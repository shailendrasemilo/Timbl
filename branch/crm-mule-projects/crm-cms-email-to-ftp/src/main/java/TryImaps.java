import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;


public class TryImaps {

	public static void main(String[] args) throws Exception {
		getImapFolderList();
		getEncodedURL("Xyz@321##");

	}
	
	private static void getEncodedURL(String string) {
		System.out.println(URLEncoder.encode(string));
	}

	private static void getImapFolderList() throws Exception {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect("outlook.office365.com", "test.nextra@nextraworld.com", "Xyz@321##");
		System.out.println(store);

		Folder[] f = store.getDefaultFolder().list();
		for(Folder fd:f)
		    System.out.println(">> "+fd.getName());
		
		f = store.getFolder("Processed").list();
		for(Folder fd:f)
		    System.out.println("Saved >> "+fd.getName());
	}

}
