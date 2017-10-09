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

public class ContatosAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<Object> contatos;
	
	public ContatosAdapter(Context context, ArrayList<Object> contatos) {
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
		View view = inflater.inflate(R.layout.lista_contatos, null);
		TextView tv_contato = (TextView) view.findViewById(R.id.listaContatos_tv_contato);

		tv_contato.setText(contato.getNome());
		
		return view;
	}

}
