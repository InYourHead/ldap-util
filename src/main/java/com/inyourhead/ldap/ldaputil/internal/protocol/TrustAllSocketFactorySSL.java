package com.inyourhead.ldap.ldaputil.internal.protocol;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;

import static com.inyourhead.ldap.ldaputil.internal.protocol.TrustManagerUtil.getTrustManagers;

public class TrustAllSocketFactorySSL extends SocketFactory {

    private final SocketFactory delegate;

    public TrustAllSocketFactorySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, getTrustAllCerts(), new java.security.SecureRandom());
        delegate = sslContext.getSocketFactory();
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return delegate.createSocket(host, port);
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        return delegate.createSocket(host, port, localHost, localPort);
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return delegate.createSocket(host, port);
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return delegate.createSocket(address, port, localAddress, localPort);
    }

    private TrustManager[] getTrustAllCerts() {
        return getTrustManagers();
    }
}
