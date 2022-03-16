import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NioSelectorTest {

    //пример однопоточного неблокируещего ввода вывода используя selector и channel
    @Test
    public void selectorTest() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        //в один  селектор можно регистрировать множество каналов
        //serverSocketChannel можно настроить только на событие OP_ACCEPT, при остальных будет ексепшн
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //тут основной поток заблокируется в ожидании когда selected будет больше 0,
            // далее все будет выполнятся в одном потоке
            int selected = selector.select();
            if (selected > 0) {
                for (var key : selector.selectedKeys()) {
                    //при первом запросе по адресу http://localhost:9999/ в консоли появится строка "Hello world"
                    //после этого можно реализовывать раздиичную логику при чтении и записи
                    //все будет выполнятся в одном потоке и без блокировки (кроме чтения и записи в файл (FileChannel))
                    if (key.isAcceptable()) {
                        System.out.println("Hello world");
                    }
                }
            }
        }
    }
}
