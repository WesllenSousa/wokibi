package br.rr.wsl.controle.adapter;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.entidades.contato.bean.ContatoBean;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClientesConectadosAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<Object> contatos;
	
	public ClientesConectadosAdapter(Context context, ArrayList<Object> contatos) {
		this.context = context;
		this.contatos = contatos;
	}

	@Override
	public int getCount() {
		return contatos.size();
	}

	@Override
	public Object getItem(int position) {
		return contatos.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContatoBean contato = (ContatoBean) contatos.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.lista_contatos_conectados, null);
		TextView tv_contato = (TextView) view.findViewById(R.id.listaContatosConectados_tv_nome);
		TextView tv_ip = (TextView) view.findViewById(R.id.listaContatosConectados_tv_ip);
		
		tv_contato.setText(contato.getNome());
		tv_ip.setText(contato.getIp());
		
		return view;
	}

}
