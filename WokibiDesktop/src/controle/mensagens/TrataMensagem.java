package controle.mensagens;

import controle.clienteServidor.Cliente;
import controle.clienteServidor.ClienteEntrada;
import controle.notificacao.Notificacao;
import controle.utilitarios.ConstantesDiversas;
import controle.utilitarios.Instancias;
import controle.utilitarios.Results;
import entidades.contato.bean.ContatoBean;
import entidades.contato.facade.ContatoFacade;
import entidades.contato.facade.ContatoFacadeImpl;
import entidades.conversa.bean.ConversaBean;
import entidades.conversa.facade.ConversaFacade;
import entidades.conversa.facade.ConversaFacadeImpl;
import entidades.mensagem.bean.MensagemBean;
import entidades.mensagem.facade.MensagemFacade;
import entidades.mensagem.facade.MensagemFacadeImpl;
import java.io.IOException;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import util.FacadeException;
import util.RedeUtil;

public class TrataMensagem {

    private Object handler;
    private ContatoFacade contatoFacade = new ContatoFacadeImpl();
    private ConversaFacade conversaFacade = new ConversaFacadeImpl();
    private MensagemFacade mensagemFacade = new MensagemFacadeImpl();

    /*
     * MENSAGENS
     *
     */
    public void verificaMensagemCliente(Cliente cliente, String mensagem) {

        try {
            if (mensagem.equals(ConstantesDiversas.CS_FIM)) {

                cliente.fecharConexao();
                System.out.println("Cliente - Cliente desconectado.");

            } else if (mensagem.equals(ConstantesDiversas.CS_CONECTADO)) {

                System.out.println("Cliente - Recebendo confirmação do servidor...");
                ArrayList<String> dados = (ArrayList<String>) cliente.getEntrada().readObject();
                String identificacaoConversa = cliente.getEntrada().readObject().toString();
                String nomeConversa = cliente.getEntrada().readObject().toString();

                ContatoBean contato = verificaContato(dados,
                        cliente.getSocket().getInetAddress().getHostAddress(),
                        cliente.getSocket().getPort(), 1); //1: autor-cliente
                ConversaBean conversa = verificaConversa(nomeConversa); 

                cliente.setContato(contato);
                cliente.setConversa(conversa);
                cliente.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));

                Instancias.getConversasServidor().put(conversa.getCodigo(), cliente);
                System.out.println("Cliente - Conversas servidor ativas: " + Instancias.getConversasServidor());
                if (handler != null) {
                    handleNovaConversa(conversa.getCodigo());
                } else {
                    receberMensagem(contato.getNome(), contato.getNome() + " conectado!", conversa.getCodigo());
                }

                cliente.enviarDados(ConstantesDiversas.CS_CONECTADO_SERVIDOR);

                System.out.println("Cliente - Cliente conectado.");

            } else if (mensagem.equals(ConstantesDiversas.CS_CONECTADO_SERVIDOR)) {

                receberMensagem("Sucesso", "Conexão confirmada!", cliente.getConversa().getCodigo());
                System.out.println("Cliente - O servidor notificou a conexão do cliente conectado.");

            } else if (mensagem.equals(ConstantesDiversas.CS_MENSAGEM)) {

                System.out.println("Cliente - Recebendo mensagem...");
                String texto = cliente.getEntrada().readObject().toString();
                String nome = cliente.getEntrada().readObject().toString();

                ContatoBean contato = selecionarContatoPorNome(nome);
                MensagemBean m = (MensagemBean) inserir(populaMensagem(texto, contato, cliente.getConversa()));
                if (handler != null) {
                    if (Results.getTelaMensagens() != null && 
                            cliente.getConversa().getCodigo() == Results.getTelaMensagens().getConversa().getCodigo()) {
                        handleNovaMensagem(m.getTexto());
                    }
                } else {
                    receberMensagem(cliente.getContato().getNome(), m.getTexto(), cliente.getConversa().getCodigo());
                }
                System.out.println("Cliente - Mensagem recebida.");

            } else if (mensagem.equals(ConstantesDiversas.CS_CLIENTE_CONECTADOS)) {

                System.out.println("Recebendo os clientes conectados!");
                ArrayList<String> clientes = (ArrayList<String>) cliente.getEntrada().readObject();
                handleClientesConectados(clientes);

            } else if (mensagem.equals(ConstantesDiversas.CS_ASSOCIAR_CONVERSA)) {

                System.out.println("Cliente - cliente recebendo solicitação de associação.");
                String ip = cliente.getEntrada().readObject().toString();
                String identificacaoConversa = cliente.getEntrada().readObject().toString();
                String nomeConversa = cliente.getEntrada().readObject().toString();

                Cliente c = new Cliente(ip, 2, nomeConversa);
                c.getTratarMensagem().setHandler(null);
                c.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
                Thread thread = new Thread(c);
                thread.start();
                System.out.println("Cliente - enviou solicitação ao servidor.");

            } else if (mensagem.equals(ConstantesDiversas.CS_CONVERSA_PRIVADA)) {

                System.out.println("Cliente - Conectando cliente conversa privada....");
                ArrayList<String> dados = (ArrayList<String>) cliente.getEntrada().readObject();
                String identificacaoConversa = cliente.getEntrada().readObject().toString();
                String nomeConversa = cliente.getEntrada().readObject().toString();

                verificaContato(dados,
                        cliente.getSocket().getInetAddress().getHostAddress(),
                        cliente.getSocket().getPort(), 1); //1: autor-cliente
                ConversaBean conversa = verificaConversa(nomeConversa); 

                //Conecta o próprio cliente
                Cliente c = new Cliente(cliente.getSocket().getInetAddress().getHostAddress(), 3, conversa.getDescricao());
                c.setContato(Instancias.getDono());
                c.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
                c.setConversa(conversa);
                Thread thread = new Thread(c);
		thread.start();

                Instancias.getConversasServidor().put(conversa.getCodigo(), c);     
                if (handler != null) {
                    handleConversaPrivada(conversa.getCodigo());
                }

            } else if (mensagem.equals(ConstantesDiversas.CS_DADOS_CONTATOS)) {
                
                ArrayList<String> dados = (ArrayList<String>) cliente.getEntrada().readObject();
                
                System.out.println("recebendo dados dos contatos associados.");
                verificaContato(dados,
                        cliente.getSocket().getInetAddress().getHostAddress(),
                        cliente.getSocket().getPort(), 1); //1: autor-cliente
                
            }

        } catch (OptionalDataException e) {
            System.out.println("TrataMensagem-Cliente: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("TrataMensagem-Cliente: " + e);
        } catch (IOException e) {
            System.out.println("TrataMensagem-Cliente: " + e);
        }

    }

    public void verificarMensagemServidor(ClienteEntrada clienteEntrada, String mensagem) {

        try {
            if (mensagem.equals(ConstantesDiversas.CS_FIM)) {

                String msg = "Até mais, fui desconectado(a)!";
                Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
                clienteEntrada.getServidor().enviarDadosTodos(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_MENSAGEM, porta);
                clienteEntrada.getServidor().enviarDadosTodos(clienteEntrada.getIdentificacaoConversa(), msg, porta);
                clienteEntrada.getServidor().enviarDadosTodos(clienteEntrada.getIdentificacaoConversa(), clienteEntrada.getContato().getNome(), porta);
                clienteEntrada.fecharConexao();
                System.out.println("Servidor - Cliente desconectado.");

            } else if (mensagem.equals(ConstantesDiversas.CS_INICIAR_CONVERSA)) {

                System.out.println("Servidor - Cliente solicitando conversa...");
                ArrayList<String> dados = (ArrayList<String>) clienteEntrada.getEntrada().readObject();
                String nomeConversa = clienteEntrada.getEntrada().readObject().toString();

                ContatoBean contato = verificaContato(dados,
                        clienteEntrada.getClienteSaida().getSocket().getInetAddress().getHostAddress(),
                        clienteEntrada.getClienteSaida().getSocket().getPort(), 1); //1: autor-cliente
                ConversaBean conversa = verificaConversa(nomeConversa);

                Integer identificacaoConversa = clienteEntrada.getServidor().gerarIdentificacao();
                clienteEntrada.setConversa(conversa);
                clienteEntrada.setContato(contato);
                clienteEntrada.setIdentificacaoConversa(identificacaoConversa);

                Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CONECTADO, porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), dadosContato(selecionarContatoDono()), porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), identificacaoConversa, porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), Instancias.getDono().getNome(), porta);
                System.out.println("Servidor - Servidor confirma cliente.");

                System.out.println("Servidor - Criando uma conversa para o servidor");
                Cliente cliente = new Cliente(RedeUtil.obtemIpLocal(), 1, nomeConversa);
                cliente.setConversa(conversa);
                cliente.setContato(Instancias.getDono());
                cliente.setIdentificacaoConversa(identificacaoConversa);
                Thread thread = new Thread(cliente);
                thread.start();

                Instancias.getConversasCliente().put(conversa.getCodigo(), cliente);
                System.out.println("Servidor - Conversas clientes ativas: " + Instancias.getConversasCliente());
                System.out.println("Servidor - Cliente local conectado.");

            } else if (mensagem.equals(ConstantesDiversas.CS_INICIAR_CONVERSA_SERVIDOR)) {

                System.out.println("Servidor - Iniciando a conversa cliente local...");
                String codigoContato = clienteEntrada.getEntrada().readObject().toString();
                String codigoConversa = clienteEntrada.getEntrada().readObject().toString();
                String identificacaoConversa = clienteEntrada.getEntrada().readObject().toString();

                ContatoBean ct = new ContatoBean();
                ct.setCodigo(Integer.parseInt(codigoContato));
                ConversaBean cv = new ConversaBean();
                cv.setCodigo(Integer.parseInt(codigoConversa));

                ContatoBean contato = selecionar(ct);
                contato.setIp(clienteEntrada.getClienteSaida().getSocket().getInetAddress().getHostAddress());
                contato.setPorta(clienteEntrada.getClienteSaida().getSocket().getPort());
                contato = inserirOuAlterar(contato);

                ConversaBean conversa = selecionar(cv);

                clienteEntrada.setContato(contato);
                clienteEntrada.setConversa(conversa);
                clienteEntrada.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
                System.out.println("Servidor - Conversa local iniciada!");

            } else if (mensagem.equals(ConstantesDiversas.CS_MENSAGEM)) {

                String texto = clienteEntrada.getEntrada().readObject().toString();
                String contato = clienteEntrada.getEntrada().readObject().toString();

                Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
                clienteEntrada.getServidor().enviarDadosTodos(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_MENSAGEM, porta);
                clienteEntrada.getServidor().enviarDadosTodos(clienteEntrada.getIdentificacaoConversa(), texto, porta);
                clienteEntrada.getServidor().enviarDadosTodos(clienteEntrada.getIdentificacaoConversa(), contato, porta);
                System.out.println("Servidor - Encaminhando mensagem. ");

            } else if (mensagem.equals(ConstantesDiversas.CS_CONECTADO_SERVIDOR)) {

                Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
                clienteEntrada.getServidor().enviarDadosTodos(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CONECTADO_SERVIDOR, porta);
                System.out.println("Servidor - Encaminhando confirmação de conexão. ");

            } else if (mensagem.equals(ConstantesDiversas.CS_CLIENTE_CONECTADOS)) {

                ArrayList<String> clientes = new ArrayList<>();
                for (ArrayList<ClienteEntrada> clientesEntradas : clienteEntrada.getServidor().getConversas().values()) {
                    for (ClienteEntrada cliente : clientesEntradas) {
                        if(!cliente.getContato().getNome().equals(Instancias.getDono().getNome())) {
                            clientes.add(cliente.getContato().toString());
                        }
                    }
	        }

                Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CLIENTE_CONECTADOS, porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), clientes, porta);
                System.out.println("Enviou clientes conectados!");

            } else if (mensagem.equals(ConstantesDiversas.CS_ASSOCIAR_CONVERSA)) {

                System.out.println("Servidor - Cliente solicitando associação...");
                ArrayList<String> dados = (ArrayList<String>) clienteEntrada.getEntrada().readObject();
                String identificacaoConversa = clienteEntrada.getEntrada().readObject().toString();
                String nomeConversa = clienteEntrada.getEntrada().readObject().toString();

                ContatoBean contato = verificaContato(dados,
                        clienteEntrada.getClienteSaida().getSocket().getInetAddress().getHostAddress(),
                        clienteEntrada.getClienteSaida().getSocket().getPort(), 1); //1: autor-cliente
                ConversaBean conversa = verificaConversa(dados.get(0)); 

                clienteEntrada.setConversa(conversa);
                clienteEntrada.setContato(contato);
                clienteEntrada.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));

                Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CONECTADO, porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), dadosContato(selecionarContatoDono()), porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), identificacaoConversa, porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), nomeConversa, porta);
                System.out.println("Servidor - Servidor confirma cliente.");

            } else if (mensagem.equals(ConstantesDiversas.CS_ENCAMINHAR_ASSOCIAR_CONVERSA)) {

                System.out.println("Servidor - encaminhando solicitação de associação.");
                String ip = clienteEntrada.getEntrada().readObject().toString();
                String identificacaoConversa = clienteEntrada.getEntrada().readObject().toString();
                String nomeConversa = clienteEntrada.getEntrada().readObject().toString();
                String contato = clienteEntrada.getEntrada().readObject().toString();

                ClienteEntrada ce = null;
                for (ArrayList<ClienteEntrada> clientesEntradas : clienteEntrada.getServidor().getConversas().values()) {
                    Boolean status = false;
                    for (ClienteEntrada cliente : clientesEntradas) {
                        if (cliente.getContato().getNome().equals(contato)) {
                            ce = cliente; 
                            status = true;
                            break;
                        }
                    }
                    if(status) {
                        break;
                    }
                }
                
                for (ArrayList<ClienteEntrada> clientesEntradas : clienteEntrada.getServidor().getConversas().values()) {
                    for (ClienteEntrada cliente : clientesEntradas) {
                        if(cliente.getIdentificacaoConversa() == Integer.parseInt(identificacaoConversa)) {
                            if(!cliente.getContato().getNome().equals(Instancias.getDono().getNome())) { 
                                //Envia para todos usuários dessa conversa os dados do cliente associado
                                Integer porta = cliente.getClienteSaida().getSocket().getPort();
                                System.out.println("Servidor - encaminhando para: "+cliente.getContato()+" os dados do cliente: "+ce.getContato());
                                cliente.getServidor().enviarDadosUm(cliente.getIdentificacaoConversa(), ConstantesDiversas.CS_DADOS_CONTATOS, porta);
                                cliente.getServidor().enviarDadosUm(cliente.getIdentificacaoConversa(), dadosContato(ce.getContato()), porta); 
                                porta = ce.getClienteSaida().getSocket().getPort();
                                System.out.println("Servidor - encaminhando para: "+ce.getContato()+" os dados do cliente: "+cliente.getContato());
                                cliente.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), ConstantesDiversas.CS_DADOS_CONTATOS, porta);
                                cliente.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), dadosContato(cliente.getContato()), porta); 
                            }
                        }
                    }
                }
                
                //Encaminha para o cliente a associação de conversa
                Integer porta = ce.getClienteSaida().getSocket().getPort();
                ce.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), ConstantesDiversas.CS_ASSOCIAR_CONVERSA, porta);
                ce.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), ip, porta);
                ce.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), identificacaoConversa, porta);
                ce.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), nomeConversa, porta);
                System.out.println("Servidor - encaminhado.");

            } else if (mensagem.equals(ConstantesDiversas.CS_CONVERSA_PRIVADA)) {

                System.out.println("Servidor - Cliente iniciando conversa privada...");
                String descricaoConversa = clienteEntrada.getEntrada().readObject().toString();
                String nomeContato = clienteEntrada.getEntrada().readObject().toString();

                ContatoBean contato = selecionarContatoPorNome(nomeContato);
                Integer identificacaoConversa = clienteEntrada.getServidor().gerarIdentificacao();
                        
                //Enviado os dados para o usuário que solicitou a conversa privada
                Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CONVERSA_PRIVADA, porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), dadosContato(contato), porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), identificacaoConversa, porta);
                clienteEntrada.getServidor().enviarDadosUm(clienteEntrada.getIdentificacaoConversa(), descricaoConversa, porta);
                
                //Enviando os dados para o usuário que foi solicitado
                ClienteEntrada ce = null;
                for (ArrayList<ClienteEntrada> clientesEntradas : clienteEntrada.getServidor().getConversas().values()) {
                    Boolean status = false;
                    for (ClienteEntrada cliente : clientesEntradas) {
                        if (cliente.getContato().getNome().equals(nomeContato)) {
                            ce = cliente; 
                            status = true;
                            break;
                        }
                    }
                    if(status) {
                        break;
                    }
                }
                porta = ce.getClienteSaida().getSocket().getPort();
                ce.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), ConstantesDiversas.CS_CONVERSA_PRIVADA, porta);
                ce.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), dadosContato(clienteEntrada.getContato()), porta);
                ce.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), identificacaoConversa, porta);
                ce.getServidor().enviarDadosUm(ce.getIdentificacaoConversa(), descricaoConversa, porta);

                System.out.println("Servidor encaminhou solicitação de conversa privada para cliente.");

            } else if (mensagem.equals(ConstantesDiversas.CS_CRIAR_CONVERSA_PRIVADA)) {
                
                System.out.println("Servidor - criando conversa privada...");
                String identificacaoConversa = clienteEntrada.getEntrada().readObject().toString();
                String nomeContato = clienteEntrada.getEntrada().readObject().toString();
                
                clienteEntrada.setContato(selecionarContatoPorNome(nomeContato));
                clienteEntrada.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
                
            }

        } catch (OptionalDataException e) {
            System.out.println("TrataMensagem-Servidor: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("TrataMensagem-Servidor: " + e);
        } catch (IOException e) {
            System.out.println("TrataMensagem-Servidor: " + e);
        }

    }

    public void enviarMensagemServidor(Cliente cliente, String tipo) {
        if (tipo.equals(ConstantesDiversas.CS_INICIAR_CONVERSA)) {
            cliente.enviarDados(ConstantesDiversas.CS_INICIAR_CONVERSA);
            cliente.enviarDados(dadosContato(selecionarContatoDono()));
            cliente.enviarDados(cliente.getNomeConversa());
        } else if (tipo.equals(ConstantesDiversas.CS_INICIAR_CONVERSA_SERVIDOR)) {
            cliente.enviarDados(ConstantesDiversas.CS_INICIAR_CONVERSA_SERVIDOR);
            cliente.enviarDados(cliente.getContato().getCodigo().toString());
            cliente.enviarDados(cliente.getConversa().getCodigo().toString());
            cliente.enviarDados(cliente.getIdentificacaoConversa().toString());
        } else if (tipo.equals(ConstantesDiversas.CS_ASSOCIAR_CONVERSA)) {
            cliente.enviarDados(ConstantesDiversas.CS_ASSOCIAR_CONVERSA);
            cliente.enviarDados(dadosContato(selecionarContatoDono()));
            cliente.enviarDados(cliente.getIdentificacaoConversa());
            cliente.enviarDados(cliente.getNomeConversa());
        } else if (tipo.equals(ConstantesDiversas.CS_CRIAR_CONVERSA_PRIVADA)) {
            cliente.enviarDados(ConstantesDiversas.CS_CRIAR_CONVERSA_PRIVADA);
            cliente.enviarDados(cliente.getIdentificacaoConversa());
            cliente.enviarDados(cliente.getContato().getNome());
        }
    }

    /*
     * OUTROS
     */
    private ContatoBean verificaContato(ArrayList<String> dados, String ip, Integer porta, Integer autor) {
        ContatoBean contato = selecionarContatoAutor(dados.get(0), autor);
        if (contato == null) {
            System.out.println("Inserindo contato...");
            contato = inserirOuAlterar(populaContatoBean(dados, ip, porta));
            return contato;
        } else {
            System.out.println("Contato já existente: " + contato.getNome());
            contato.setStatus(0);
            contato.setIp(ip);
            contato.setPorta(porta);
            ContatoBean c = inserirOuAlterar(contato);
            return c;
        }
    }

    private ConversaBean verificaConversa(String descricaoConversa) {
        ConversaBean conversa = selecionarConversaPorDescricao(descricaoConversa);
        if (conversa == null) {
            System.out.println("Inserindo conversa...");
            conversa = inserirOuAlterar(populaConversaBean(descricaoConversa));
            return conversa;
        } else {
            System.out.println("Conversa existente: " + conversa.getDescricao());
            conversa.setStatus(0);
            ConversaBean c = inserirOuAlterar(conversa);
            return c;
        }
    }

    private ContatoBean populaContatoBean(ArrayList<String> dados, String ip, Integer porta) {
        ContatoBean contato = new ContatoBean();

        contato.setNome(dados.get(0));
        contato.setDdd(Integer.parseInt(dados.get(1)));
        contato.setCelular(Integer.parseInt(dados.get(2)));
        contato.setEmail(dados.get(3));
        contato.setAutor(1);
        contato.setDeletar(0);
        contato.setStatus(0);
        contato.setIp(ip);
        contato.setPorta(porta);

        return contato;
    }

    private ConversaBean populaConversaBean(String descricao) {
        ConversaBean conversa = new ConversaBean();

        conversa.setDataHora(Calendar.getInstance());
        conversa.setDescricao(descricao);
        conversa.setDeletar(0);
        conversa.setStatus(0);

        return conversa;
    }

    private MensagemBean populaMensagem(String mensagem, ContatoBean contato, ConversaBean conversa) {
        MensagemBean bean = new MensagemBean();

        bean.setContato(contato);
        bean.setConversa(conversa);
        bean.setDataHora(Calendar.getInstance());
        bean.setDeletar(0);
        bean.setOcultar(0);
        bean.setTexto(mensagem);

        return bean;
    }

    private ArrayList<String> dadosContato(ContatoBean contato) {
        if (contato != null) {
            ArrayList<String> dados = new ArrayList<>();
            dados.add(contato.getNome());
            dados.add(contato.getDdd().toString());
            dados.add(contato.getCelular().toString());
            dados.add(contato.getEmail());
            return dados;
        }
        return null;
    }

    public void desconectarConversa(ConversaBean conversa) {
        if(conversa != null) {
            conversa.setStatus(1);
            ConversaBean c = inserirOuAlterar(conversa);
            if (c != null) {
                if (Instancias.getConversasCliente().containsKey(conversa.getCodigo())) {
                    Instancias.getConversasCliente().remove(conversa.getCodigo());
                } else if (Instancias.getConversasServidor().containsKey(conversa.getCodigo())) {
                    Instancias.getConversasServidor().remove(conversa.getCodigo());
                }
                System.out.println("Conversa desconectada: " + conversa.getCodigo());
            }
        }
    }

    public void desconectarContato(ContatoBean contato) {
        contato.setStatus(1);
        if (inserirOuAlterar(contato) != null) {
            System.out.println("Contato desconectado: " + contato.getCodigo());
        }
    }

    public void handleNovaConversa(Integer codigoConversa) {
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa + "");
        Results.getTelaNovaConversaCliente().onResult(ConstantesDiversas.HD_NOVA_CONVERSA, mapa);
    }

    public void handleNovaMensagem(String mensagem) {
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put(ConstantesDiversas.BD_MENSAGEM, mensagem);
        Results.getTelaMensagens().onResult(ConstantesDiversas.HD_NOVA_MENSAGEM, mapa);
    }

    public void handleClientesConectados(ArrayList<String> clientes) {
        HashMap<String, Object> mapa = new HashMap<>();
        mapa.put(ConstantesDiversas.BD_CODIGO_CONVERSA, clientes);
        Results.getTelaClientesConectados().onResult(ConstantesDiversas.HD_RETORNA_CLIENTES, mapa);
    }

    public void handleConversaPrivada(Integer codigoConversa) {
        HashMap<String, Object> mapa = new HashMap<>();
        mapa.put(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa);
        if(Results.getTelaClientesConectados() != null) {
            Results.getTelaClientesConectados().onResult(ConstantesDiversas.HD_CONVERSA_PRIVADA, mapa);
        }
    }

    private void receberMensagem(String nomeContato, String mensagem, Integer codigoConversa) {
        Notificacao notificacao = new Notificacao();
        notificacao.setCodigoConversa(codigoConversa);
        notificacao.setNome(nomeContato);
        notificacao.setMensagem(mensagem);
        Instancias.getNotificacoes().add(notificacao);
        Instancias.getTelaPrincipal().lb_notificacao.setVisible(true);
    }

    /*
     * BANCO DE DADOS
     *
     */
    private ConversaBean selecionarConversaPorDescricao(String descricao) {
        try {
            return (ConversaBean) conversaFacade.selecionarConversaPorDescricao(descricao);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ContatoBean selecionarContatoAutor(String nome, Integer autor) {
        try {
            return (ContatoBean) contatoFacade.selecionarContatoAutor(nome, autor);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ContatoBean selecionarContatoDono() {
        try {
            return (ContatoBean) contatoFacade.selecionarContatoDono();
        } catch (FacadeException e) {
        }
        return null;
    }

    private ContatoBean selecionarContatoPorNome(String nome) {
        try {
            return (ContatoBean) contatoFacade.selecionarContatoPorNome(nome);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ContatoBean selecionar(ContatoBean contato) {
        try {
            return (ContatoBean) contatoFacade.selecionar(contato);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ConversaBean selecionar(ConversaBean conversa) {
        try {
            return (ConversaBean) conversaFacade.selecionar(conversa);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ConversaBean inserirOuAlterar(ConversaBean conversa) {
        try {
            return (ConversaBean) conversaFacade.inserirOuAlterar(conversa);
        } catch (FacadeException e) {
        }
        return null;
    }

    private ContatoBean inserirOuAlterar(ContatoBean contato) {
        try {
            return (ContatoBean) contatoFacade.inserirOuAlterar(contato);
        } catch (FacadeException e) {
        }
        return null;
    }

    private Object inserir(MensagemBean mensagem) {
        try {
            return mensagemFacade.inserir(mensagem);
        } catch (FacadeException e) {
        }
        return null;
    }

    /*
     * GETTERS e SETTES
     */
    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }
    
}
