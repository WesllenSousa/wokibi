package br.rr.wsl.controle.mensagens;

import java.io.IOException;
import java.io.OptionalDataException;
import java.security.Identity;
import java.util.ArrayList;
import java.util.Calendar;

import br.rr.wsl.controle.clienteServidor.Cliente;
import br.rr.wsl.controle.clienteServidor.ClienteEntrada;
import br.rr.wsl.controle.utilitarios.ConstantesDiversas;
import br.rr.wsl.controle.utilitarios.Instancias;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import br.rr.wsl.entidades.contato.facade.ContatoFacade;
import br.rr.wsl.entidades.contato.facade.ContatoFacadeImpl;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;
import br.rr.wsl.entidades.conversa.facade.ConversaFacade;
import br.rr.wsl.entidades.conversa.facade.ConversaFacadeImpl;
import br.rr.wsl.entidades.mensagem.bean.MensagemBean;
import br.rr.wsl.entidades.mensagem.facade.MensagemFacade;
import br.rr.wsl.entidades.mensagem.facade.MensagemFacadeImpl;
import br.rr.wsl.util.FacadeException;
import br.rr.wsl.util.RedeUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class TrataMensagem {
	
	private static final String CATEGORIA = "wsl";
	
	private Handler handler;
	private ContatoFacade contatoFacade = new ContatoFacadeImpl(Instancias.getContext());
	private ConversaFacade conversaFacade = new ConversaFacadeImpl(Instancias.getContext());
	private MensagemFacade mensagemFacade = new MensagemFacadeImpl(Instancias.getContext());
	
	/*
	 * MENSAGENS
	 * */
	public void verificaMensagemCliente(Cliente cliente, String mensagem) {
		
		try {
			if(mensagem.equals(ConstantesDiversas.CS_FIM)) {
				
	            cliente.fecharConexao();
	            Log.d(CATEGORIA, "Cliente - Cliente desconectado.");
	            
	        } else if(mensagem.equals(ConstantesDiversas.CS_CONECTADO)) {
	        	
	        	Log.d(CATEGORIA, "Cliente -  Conectando cliente....");    
	        	ArrayList<String> dados = (ArrayList<String>) cliente.getEntrada().readObject();
	        	String identificacaoConversa = cliente.getEntrada().readObject().toString();
	        	String nomeConversa = cliente.getEntrada().readObject().toString();
				
				ContatoBean contato = verificaContato(dados,    
						cliente.getSocket().getInetAddress().getHostAddress(), 
						cliente.getSocket().getPort(), 1); //1: autor-cliente
				ConversaBean conversa = verificaConversa(nomeConversa, 1); //1: Conversa servidor
			
	        	cliente.setContato(contato);
	        	cliente.setConversa(conversa);
	        	cliente.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
	        
	        	Instancias.getConversasServidor().put(conversa.getCodigo(), cliente); 
	        	Log.d(CATEGORIA, "Conversas servidor ativas: "+Instancias.getConversasServidor());
	        	if(handler != null) {
	        		handleNovaConversa(conversa.getCodigo());
	        	} else {
	        		receberMensagem(contato.getNome(), contato.getNome()+" conectado!", conversa.getCodigo());
	        	}
	        	
	        	cliente.enviarDados(ConstantesDiversas.CS_CONECTADO_SERVIDOR);
	        	
	        	Log.d(CATEGORIA, "Cliente - Cliente conectado.");     
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_CONECTADO_SERVIDOR)) {
	         	
	        	receberMensagem("Sucesso", "Conexão confirmada!", cliente.getConversa().getCodigo());
	        	Log.d(CATEGORIA, "Cliente - O servidor notificou a conexão do cliente conectado.");  	
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_MENSAGEM)) {
	        	
	        	Log.d(CATEGORIA, "Cliente - Recebendo mensagem... ");
	        	String texto = cliente.getEntrada().readObject().toString();
	        	String nome = cliente.getEntrada().readObject().toString();
	        	
	        	ContatoBean contato = selecionarContatoPorNome(nome);
	        	MensagemBean m = (MensagemBean) inserir(populaMensagem(texto, contato, cliente.getConversa()));
	        	if(handler != null) {
	        		handleNovaMensagem(m.getTexto());
	        	} else {
	        		receberMensagem(cliente.getContato().getNome(), m.getTexto(), cliente.getConversa().getCodigo());
	        	}
	        	Log.d(CATEGORIA, "Cliente - Mensagem recebida.");
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_CLIENTE_CONECTADOS)) {
	        	
	        	Log.v(CATEGORIA, "Recebendo os clientes conectados!");
	        	ArrayList<String> clientes = (ArrayList<String>) cliente.getEntrada().readObject();
	        	handleClientesConectados(clientes);
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_ASSOCIAR_CONVERSA)) {
	        	
	        	Log.v(CATEGORIA, "Cliente - cliente recebendo solicitação de associação.");
	        	String ip = cliente.getEntrada().readObject().toString();
	        	String identificacaoConversa = cliente.getEntrada().readObject().toString();
	        	String nomeConversa = cliente.getEntrada().readObject().toString(); 
	        	
	        	Cliente c = new Cliente(ip, 2, nomeConversa);
				c.getTratarMensagem().setHandler(null);
				c.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
				Thread thread = new Thread(c);
				thread.start();
				Log.v(CATEGORIA, "Cliente - enviou solicitação ao servidor.");
	        	
	        }  else if(mensagem.equals(ConstantesDiversas.CS_CONVERSA_PRIVADA)) {
	        	
	        	Log.d(CATEGORIA, "Cliente -  Conectando cliente conversa privada....");    
	        	ArrayList<String> dados = (ArrayList<String>) cliente.getEntrada().readObject();
                String identificacaoConversa = cliente.getEntrada().readObject().toString();
                String nomeConversa = cliente.getEntrada().readObject().toString();

                verificaContato(dados,
                        cliente.getSocket().getInetAddress().getHostAddress(),
                        cliente.getSocket().getPort(), 1); //1: autor-cliente
                ConversaBean conversa = verificaConversa(nomeConversa, 1); //1: Conversa servidor

                //Conecta o próprio cliente
                Cliente c = new Cliente(cliente.getSocket().getInetAddress().getHostAddress(), 3, conversa.getDescricao());
                c.setContato(Instancias.getDono());
                c.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
                c.setConversa(conversa);
                Thread thread = new Thread(c);
				thread.start();
	        	
                Instancias.getConversasServidor().put(conversa.getCodigo(), c);
	        	if(handler != null) {
	        		handleConversaPrivada(conversa.getCodigo());
	        	}
	        	
	        } else if (mensagem.equals(ConstantesDiversas.CS_DADOS_CONTATOS)) {
                
                ArrayList<String> dados = (ArrayList<String>) cliente.getEntrada().readObject();
                
                Log.d(CATEGORIA, "recebendo dados dos contatos associados.");
                verificaContato(dados,
                        cliente.getSocket().getInetAddress().getHostAddress(),
                        cliente.getSocket().getPort(), 1); //1: autor-cliente
                
            }
			
		} catch (OptionalDataException e) {
			Log.e(CATEGORIA, "TrataMensagem-Cliente: "+e);
		} catch (ClassNotFoundException e) {
			Log.e(CATEGORIA, "TrataMensagem-Cliente: "+e);
		} catch (IOException e) {
			Log.e(CATEGORIA, "TrataMensagem-Cliente: "+e);
		}
		
	}
	
	public void verificarMensagemServidor(ClienteEntrada clienteEntrada, String mensagem) {
		
		try {
			if(mensagem.equals(ConstantesDiversas.CS_FIM)) {
				
	        	String msg = "Até mais, fui desconectado(a)!";	        	
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_MENSAGEM);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), msg);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), clienteEntrada.getContato().getNome());
	        	clienteEntrada.fecharConexao();
	        	Log.d(CATEGORIA, "Servidor - Cliente desconectado.");
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_INICIAR_CONVERSA)) {
	        	
	        	Log.v(CATEGORIA, "Servidor - Cliente solicitando conversa...");
				ArrayList<String> dados = (ArrayList<String>) clienteEntrada.getEntrada().readObject();
				String nomeConversa = clienteEntrada.getEntrada().readObject().toString();
				
				ContatoBean contato = verificaContato(dados, 
						clienteEntrada.getClienteSaida().getSocket().getInetAddress().getHostAddress(),  
						clienteEntrada.getClienteSaida().getSocket().getPort(), 1); //1: autor-cliente
				ConversaBean conversa = verificaConversa(nomeConversa, 0); //0: Conversa cliente
				
				Integer identificacaoConversa = clienteEntrada.getServidor().gerarIdentificacao();
	        	clienteEntrada.setConversa(conversa);
	        	clienteEntrada.setContato(contato);
	        	clienteEntrada.setIdentificacaoConversa(identificacaoConversa);
	        	
	        	Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CONECTADO, porta);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), dadosContato(selecionarContatoDono()), porta);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), identificacaoConversa, porta);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), Instancias.getDono().getNome(), porta);
	        	Log.v(CATEGORIA, "Servidor - Servidor confirma cliente.");
	        	
        		Log.v(CATEGORIA, "Servidor - Criando uma conversa para o servidor");
	        	Cliente cliente = new Cliente(RedeUtil.obtemIp(Instancias.getContext()), 1, nomeConversa);
	        	cliente.setConversa(conversa);
	        	cliente.setContato(Instancias.getDono());
	        	cliente.setIdentificacaoConversa(identificacaoConversa);
	    		Thread thread = new Thread(cliente);
	    		thread.start();
	    		
	    		Instancias.getConversasCliente().put(conversa.getCodigo(), cliente); 
	    		Log.d(CATEGORIA, "Conversas cliente ativas: "+Instancias.getConversasCliente());
	        	Log.d(CATEGORIA, "Servidor - Cliente local conectado.");       	
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_INICIAR_CONVERSA_SERVIDOR)) { 
	        	
	        	Log.v(CATEGORIA, "Servidor - Iniciando a conversa cliente local...");
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
	        	Log.v(CATEGORIA, "Servidor - Conversa local iniciada!");
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_MENSAGEM)) {
	        		
	        	String texto = clienteEntrada.getEntrada().readObject().toString();   
	        	String contato = clienteEntrada.getEntrada().readObject().toString();  
	        	
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_MENSAGEM);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), texto);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), contato);
	        	Log.v(CATEGORIA, "Servidor - Encaminhando mensagem. ");
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_CONECTADO_SERVIDOR)) {
	        
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CONECTADO_SERVIDOR);
	        	Log.v(CATEGORIA, "Servidor - Encaminhando confirmação de conexão. ");
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_CLIENTE_CONECTADOS)) {
	        	
	        	ArrayList<String> clientes = new ArrayList<String>();
	        	for (ArrayList<ClienteEntrada> clientesEntradas : clienteEntrada.getServidor().getConversas().values()) {
                    for (ClienteEntrada cliente : clientesEntradas) {
                        if(!cliente.getContato().getNome().equals(Instancias.getDono().getNome())) {
                            clientes.add(cliente.getContato().toString());
                        }
                    }
	        	}

	        	Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CLIENTE_CONECTADOS, porta);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), clientes, porta);
	        	Log.v(CATEGORIA, "Enviou clientes conectados!");
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_ASSOCIAR_CONVERSA)) {
	        	
	        	Log.v(CATEGORIA, "Servidor - Cliente solicitando associação...");
	        	ArrayList<String> dados = (ArrayList<String>) clienteEntrada.getEntrada().readObject();
	        	String identificacaoConversa = clienteEntrada.getEntrada().readObject().toString();
	        	String nomeConversa = clienteEntrada.getEntrada().readObject().toString();
	        	
	        	ContatoBean contato = verificaContato(dados, 
						clienteEntrada.getClienteSaida().getSocket().getInetAddress().getHostAddress(),  
						clienteEntrada.getClienteSaida().getSocket().getPort(), 1); //1: autor-cliente
				ConversaBean conversa = verificaConversa(dados.get(0), 0); //0: Conversa cliente
				
				Log.v(CATEGORIA, "Conversa: "+conversa.getDescricao());
				
				clienteEntrada.setConversa(conversa);
	        	clienteEntrada.setContato(contato);
	        	clienteEntrada.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
	        	
	        	Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CONECTADO, porta);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), dadosContato(selecionarContatoDono()), porta);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), identificacaoConversa, porta);
	        	clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), nomeConversa, porta); 
	        	Log.v(CATEGORIA, "Servidor - Servidor confirma cliente.");
	        	
	        } else if(mensagem.equals(ConstantesDiversas.CS_ENCAMINHAR_ASSOCIAR_CONVERSA)) {
	        	
	        	Log.v(CATEGORIA, "Servidor - encaminhando solicitação de associação.");
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
                    if(status) break;
                }
                
                for (ArrayList<ClienteEntrada> clientesEntradas : clienteEntrada.getServidor().getConversas().values()) {
                    for (ClienteEntrada cliente : clientesEntradas) {
                        if(cliente.getIdentificacaoConversa() == Integer.parseInt(identificacaoConversa)) {
                            if(!cliente.getContato().getNome().equals(Instancias.getDono().getNome())) { 
                                //Envia para todos usuários dessa conversa os dados do cliente associado
                                Integer porta = cliente.getClienteSaida().getSocket().getPort();
                                Log.v(CATEGORIA, "Servidor - encaminhando para: "+cliente.getContato()+" os dados do cliente: "+ce.getContato());
                                cliente.getServidor().enviarDados(cliente.getIdentificacaoConversa(), ConstantesDiversas.CS_DADOS_CONTATOS, porta);
                                cliente.getServidor().enviarDados(cliente.getIdentificacaoConversa(), dadosContato(ce.getContato()), porta); 
                                porta = ce.getClienteSaida().getSocket().getPort();
                                Log.v(CATEGORIA, "Servidor - encaminhando para: "+ce.getContato()+" os dados do cliente: "+cliente.getContato());
                                cliente.getServidor().enviarDados(ce.getIdentificacaoConversa(), ConstantesDiversas.CS_DADOS_CONTATOS, porta);
                                cliente.getServidor().enviarDados(ce.getIdentificacaoConversa(), dadosContato(cliente.getContato()), porta); 
                            }
                        }
                    }
                }
	        	
	        	//Encaminha para o cliente a associação de conversa
	        	Integer porta = ce.getClienteSaida().getSocket().getPort();
	        	ce.getServidor().enviarDados(ce.getIdentificacaoConversa(), ConstantesDiversas.CS_ASSOCIAR_CONVERSA, porta);
	        	ce.getServidor().enviarDados(ce.getIdentificacaoConversa(), ip, porta);
	        	ce.getServidor().enviarDados(ce.getIdentificacaoConversa(), identificacaoConversa, porta);
	        	ce.getServidor().enviarDados(ce.getIdentificacaoConversa(), nomeConversa, porta);
	        	Log.v(CATEGORIA, "Servidor - encaminhado.");
				
	        } else if(mensagem.equals(ConstantesDiversas.CS_CONVERSA_PRIVADA)) {
	        	
	        	Log.v(CATEGORIA, "Servidor - Cliente iniciando conversa privada...");
	        	String descricaoConversa = clienteEntrada.getEntrada().readObject().toString();
                String nomeContato = clienteEntrada.getEntrada().readObject().toString();

                ContatoBean contato = selecionarContatoPorNome(nomeContato);
                Integer identificacaoConversa = clienteEntrada.getServidor().gerarIdentificacao();

                //Enviado os dados para o usuário que solicitou a conversa privada
                Integer porta = clienteEntrada.getClienteSaida().getSocket().getPort();
                clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), ConstantesDiversas.CS_CONVERSA_PRIVADA, porta);
                clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), dadosContato(contato), porta);
                clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), identificacaoConversa, porta);
                clienteEntrada.getServidor().enviarDados(clienteEntrada.getIdentificacaoConversa(), descricaoConversa, porta);
                
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
                    if(status) break;
                }
                porta = ce.getClienteSaida().getSocket().getPort();
                ce.getServidor().enviarDados(ce.getIdentificacaoConversa(), ConstantesDiversas.CS_CONVERSA_PRIVADA, porta);
                ce.getServidor().enviarDados(ce.getIdentificacaoConversa(), dadosContato(clienteEntrada.getContato()), porta);
                ce.getServidor().enviarDados(ce.getIdentificacaoConversa(), identificacaoConversa, porta);
                ce.getServidor().enviarDados(ce.getIdentificacaoConversa(), descricaoConversa, porta);
	        	
	        	Log.v(CATEGORIA, "Servidor encaminhou solicitação de conversa privada para cliente.");
	        	     	
	        } else if (mensagem.equals(ConstantesDiversas.CS_CRIAR_CONVERSA_PRIVADA)) {
                
	        	Log.v(CATEGORIA, "Servidor - criando conversa privada...");
                String identificacaoConversa = clienteEntrada.getEntrada().readObject().toString();
                String nomeContato = clienteEntrada.getEntrada().readObject().toString();
                
                clienteEntrada.setContato(selecionarContatoPorNome(nomeContato));
                clienteEntrada.setIdentificacaoConversa(Integer.parseInt(identificacaoConversa));
                
            }
			
		} catch (OptionalDataException e) {
			Log.e(CATEGORIA, "TrataMensagem-Servidor: "+e);
		} catch (ClassNotFoundException e) {
			Log.e(CATEGORIA, "TrataMensagem-Servidor: "+e);
		} catch (IOException e) {
			Log.e(CATEGORIA, "TrataMensagem-Servidor: "+e);
		}
		
	}
	
	public void enviarMensagemServidor(Cliente cliente, String tipo) {
		if(tipo.equals(ConstantesDiversas.CS_INICIAR_CONVERSA)) {
			cliente.enviarDados(ConstantesDiversas.CS_INICIAR_CONVERSA);
			cliente.enviarDados(dadosContato(selecionarContatoDono()));
			cliente.enviarDados(cliente.getNomeConversa());
		} else if(tipo.equals(ConstantesDiversas.CS_INICIAR_CONVERSA_SERVIDOR)) {
			cliente.enviarDados(ConstantesDiversas.CS_INICIAR_CONVERSA_SERVIDOR);
			cliente.enviarDados(cliente.getContato().getCodigo().toString());
			cliente.enviarDados(cliente.getConversa().getCodigo().toString());
			cliente.enviarDados(cliente.getIdentificacaoConversa().toString());
		} else if(tipo.equals(ConstantesDiversas.CS_ASSOCIAR_CONVERSA)) {
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
	 * */
	
	private ContatoBean verificaContato(ArrayList<String> dados, String ip, Integer porta, Integer autor) {
		ContatoBean contato = selecionarContatoAutor(dados.get(0), autor);
		if(contato == null) {
			Log.i(CATEGORIA, "Inserindo contato...");
			contato = inserirOuAlterar(populaContatoBean(dados, ip, porta));
			return contato;
		} else {
			Log.i(CATEGORIA, "Contato já existente: "+contato.getNome());
			contato.setStatus(0);
			contato.setIp(ip);
			contato.setPorta(porta);
			ContatoBean c = inserirOuAlterar(contato);
			return c;
		}
	}
	
	private ConversaBean verificaConversa(String descricaoConversa, Integer clienteServidor) {
		ConversaBean conversa = selecionarConversaPorDescricao(descricaoConversa, clienteServidor);
    	if(conversa == null) {	
    		Log.i(CATEGORIA, "Inserindo conversa...");
    		conversa = inserirOuAlterar(populaConversaBean(descricaoConversa, clienteServidor));
    		return conversa;
    	} else {
    		Log.i(CATEGORIA, "Conversa existente: " + conversa.getDescricao() + ", cs: " + clienteServidor);
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
	
	private ConversaBean populaConversaBean(String descricao, Integer clienteServidor) {
		ConversaBean conversa = new ConversaBean();
		
		conversa.setDataHora(Calendar.getInstance());
		conversa.setDescricao(descricao);
		conversa.setClienteServidor(clienteServidor);
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
		if(contato != null) {
			ArrayList<String> dados = new ArrayList<String>();
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
	        if(c != null) {
	        	if(Instancias.getConversasCliente().containsKey(conversa.getCodigo())) {
	        		Instancias.getConversasCliente().remove(conversa.getCodigo());
	        	} else if(Instancias.getConversasServidor().containsKey(conversa.getCodigo())) {
	        		Instancias.getConversasServidor().remove(conversa.getCodigo());
	        	}
	        	Log.i(CATEGORIA, "Conversa desconectada: "+conversa.getCodigo());
	        }
		}
	}
	
	public void desconectarContato(ContatoBean contato) {
		contato.setStatus(1);
		if(inserirOuAlterar(contato) != null) {
			Log.i(CATEGORIA, "Contato desconectado: "+contato.getCodigo());
		}
	}
	
	public void handleNovaConversa(Integer codigoConversa) {
    	Bundle bundle = new Bundle();
    	bundle.putInt(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa);
    	Message message = new Message();
    	message.what = ConstantesDiversas.HD_NOVA_CONVERSA;
    	message.setData(bundle);
    	handler.sendMessage(message);
    }
	
	public void handleNovaMensagem(String mensagem) {
    	Bundle bundle = new Bundle();
    	bundle.putString(ConstantesDiversas.BD_MENSAGEM, mensagem);
    	Message message = new Message();
    	message.what = ConstantesDiversas.HD_NOVA_MENSAGEM;
    	message.setData(bundle);
    	handler.sendMessage(message);
    }
	
	public void handleClientesConectados(ArrayList<String> clientes) {
    	Bundle bundle = new Bundle();
    	bundle.putStringArrayList(ConstantesDiversas.BD_CODIGO_CONVERSA, clientes);
    	Message message = new Message();
    	message.what = ConstantesDiversas.HD_RETORNA_CLIENTES;
    	message.setData(bundle);
    	handler.sendMessage(message);
    }
	
	public void handleConversaPrivada(Integer codigoConversa) {
    	Bundle bundle = new Bundle();
    	bundle.putInt(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa);
    	Message message = new Message();
    	message.what = ConstantesDiversas.HD_CONVERSA_PRIVADA;
    	message.setData(bundle);
    	handler.sendMessage(message);
    }
	
	private void receberMensagem(String nomeContato, String mensagem, Integer codigoConversa) {
		Intent intent = new Intent(ConstantesDiversas.RC_RECEBER_MENSAGEM);
		intent.putExtra(ConstantesDiversas.BD_NOME_CONTATO, nomeContato);
		intent.putExtra(ConstantesDiversas.BD_MENSAGEM, mensagem);
		intent.putExtra(ConstantesDiversas.BD_CODIGO_CONVERSA, codigoConversa);
		Instancias.getContext().sendBroadcast(intent);
	}

	/*
	 * BANCO DE DADOS
	 * */
	
	private ConversaBean selecionarConversaPorDescricao(String descricao, Integer clienteServidor) {
		try {
			return (ConversaBean) conversaFacade.selecionarConversaPorDescricao(descricao, clienteServidor);
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
	 * GETTERS
	 * */
	
	public Handler getHandler() {
		return handler;
	}
	
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
