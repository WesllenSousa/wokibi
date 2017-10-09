package br.rr.wsl.controle.adapter;

import java.util.ArrayList;

import br.rr.wsl.R;
import br.rr.wsl.entidades.mensagem.bean.MensagemBean;
import br.rr.wsl.util.DataUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MensagensAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Object> mensagens;
	private String dataAnterior;
	
	public MensagensAdapter(Context context, ArrayList<Object> mensagens) {
		this.context = context;
		this.mensagens = mensagens;
	}

	@Override
	public int getCount() {
		return mensagens.size();
	}

	@Override
	public Object getItem(int position) {
		return mensagens.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MensagemBean mensagem = (MensagemBean) mensagens.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.lista_mensagens, null);
		TextView tv_barra = (TextView)view.findViewById(R.id.listaMensagem_tv_barra);
		TextView tv_titulo = (TextView)view.findViewById(R.id.listaMensagem_tv_titulo);
		TextView tv_mensagem = (TextView) view.findViewById(R.id.listaMensagem_tv_compMensagem);
		
		String dataHora = DataUtil.formataData(mensagem.getDataHora());
		if(!dataHora.equals(dataAnterior)) {
			tv_barra.setVisibility(View.VISIBLE);
			tv_barra.setText(dataHora);
			dataAnterior = dataHora;
		}
		tv_titulo.setText(mensagem.getContato().getNome());
		tv_mensagem.setText(mensagem.getTexto());
		
		return view;
	}
	
}
