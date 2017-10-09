package br.rr.wsl.controle.contatos;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class RecuperarContatos {

	private Context ctx;

	public RecuperarContatos(Context contexto) {
		this.ctx = contexto;
	}

	public List<Contato> getContatos() {
		Cursor cursor = this.ctx.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, 
				null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
		// pega os index das colunnas
		int IndexID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
		int IndexTemTelefone = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
		int IndexName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

		List<Contato> contatos = new ArrayList<Contato>();
		Contato contato;
		while (cursor.moveToNext()) {
			contato = new Contato();
			contato.setID(cursor.getString(IndexID));
			contato.setNome(cursor.getString(IndexName));
			// verifica se o contato tem telefone
			if (Integer.parseInt(cursor.getString(IndexTemTelefone)) > 0) {
				contato.setTelefones(getTelefones(contato.getID()));
			}
			contatos.add(contato);
		}
		cursor.close();
		return contatos;
	}
	
	public Contato getContato(Uri uri) {
		Cursor cursor = this.ctx.getContentResolver().query(uri, null, null, null, null);
		// pega os index das colunnas
		
		Contato contato = null;
		if (cursor.moveToNext()) {
			int IndexID = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID);
			int IndexTemTelefone = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER);
			int IndexName = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
			
			contato = new Contato();
			contato.setID(cursor.getString(IndexID));
			contato.setNome(cursor.getString(IndexName));
			// verifica se o contato tem telefone
			if (Integer.parseInt(cursor.getString(IndexTemTelefone)) > 0) {
				contato.setTelefones(getTelefones(contato.getID()));
				contato.setEmail(getEmail(contato.getID()));
			}
		}
		cursor.close();
		return contato;
	}
	
	public List<Telefone> getTelefones(String IDContato) {
          Cursor cursor =this.ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
        		  null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + IDContato, null, null);
          int IndexTelefone;
          List<Telefone> telefones = new ArrayList<Telefone>();     
          while(cursor.moveToNext()) {
                Telefone Telefone = new Telefone();                 
                IndexTelefone = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);                 
                Telefone.setTelefone(cursor.getString(IndexTelefone));  
                telefones.add(Telefone);
          }           
          cursor.close();
          return telefones;
    }
	
	public String getEmail(String IDContato) {
        Cursor cursor =this.ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
      		  null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + IDContato, null, null);
        int IndexEmail;  
        String email = "";
        while(cursor.moveToNext()) {         
              IndexEmail = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME);                 
              email = cursor.getString(IndexEmail);  
        }           
        cursor.close();
        return email;
  }

}
