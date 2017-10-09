package br.rr.wsl.controle.adapter;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.controle.clienteServidor.Cliente;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ServidoresConectadosAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<Cliente> clientes;
	
	public ServidoresConectadosAdapter(Context context, ArrayList<Cliente> clientes) {
		this.context = context;
		this.clientes = clientes;
	}

	@Override
	public int getCount() {
		return clientes.size();
	}

	@Override
	public Object getItem(int position) {
		return clientes.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Cliente cliente = clientes.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.lista_contatos_conectados, null);
		TextView tv_contato = (TextView) view.findViewById(R.id.listaContatosConectados_tv_nome);
		TextView tv_ip = (TextView) view.findViewById(R.id.listaContatosConectados_tv_ip);
		
		tv_contato.setText(cliente.getContato().getNome());
		tv_ip.setText(cliente.getContato().getIp());
		
		return view;
	}

}
