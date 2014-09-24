package edu.nju.onlinefinance.factory;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBFactory {
	public static Object getEJB(String jndipath) {
		try {
			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			jndiProperties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			return context.lookup(jndipath);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
