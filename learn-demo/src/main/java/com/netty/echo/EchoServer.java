package com.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Chen Xiao
 * @since 2021-08-23 09:40
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
//        if (args.length != 1) {
//            System.err.println("Usage:" + EchoServer.class.getSimpleName());
//
//        }
        int port = Integer.valueOf(8022);
        new EchoServer(port).start();
    }

    private void start() throws InterruptedException {

        final EchoServerHandler serverHandler = new EchoServerHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)//指定nio传输channel
                    .localAddress(new InetSocketAddress(port))//指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer<SocketChannel>() {//添加echoserverHandler到子channel的channelPipeLine
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);//EchoServerHandler标注为@Shareable,所以我们可以总是使用同样的实例
                        }
                    });

            ChannelFuture future = b.bind().sync();//异步绑定服务器，调用sync方法阻塞，等待直到绑定完成
            future.channel().closeFuture().sync();//获取channel的closefuture并且阻塞当前线程直到他完成
        } catch (Exception e) {

        } finally {
            group.shutdownGracefully().sync();//关闭eventLoopGroup
        }


    }
}

