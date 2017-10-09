package br.rr.wsl.controle.adapter;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.entidades.conversa.bean.ConversaBean;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConversasAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Object> conversas;
	
	public ConversasAdapter(Context context, ArrayList<Object> conversas) {
		this.context = context;
		this.conversas = conversas;
	}

	@Override
	public int getCount() {
		return conversas.size();
	}

	@Override
	public Object getItem(int position) {
		return conversas.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;   
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ConversaBean conversa = (ConversaBean) conversas.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.lista_conversas, null);
		TextView tv_contato = (TextView) view.findViewById(R.id.listaConversa_tv_conversa);
		ImageView img = (ImageView) view.findViewById(R.id.listaConversa_iv_imagem);
		   
		tv_contato.setText(conversa.getDescricao());
		if(conversa.getClienteServidor() == 0) {
			if(conversa.getStatus() == 0) img.setImageResource(R.drawable.cliente_ativo_50);
			else img.setImageResource(R.drawable.cliente_inativo_50);
		} else if(conversa.getClienteServidor() == 1) {
			if(conversa.getStatus() == 0) img.setImageResource(R.drawable.servidor_ativo_50);
			else img.setImageResource(R.drawable.servidor_inativo_50);
		}
		
		return view;
	}
	
}
