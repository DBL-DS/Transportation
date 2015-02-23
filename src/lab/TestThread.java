package lab;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
/**
 * 测试扩展Thread类实现的多线程程序
 *
 * @author leizhimin 2008-9-13 18:22:13
 */
public class TestThread extends Thread{
public TestThread(String name) {
        super(name);
        }

        public void run() {
        for(int i = 0;i<5;i++){
        for(long k= 0; k <100000000;k++);
        System.out.println(this.getName()+" :"+i);
        }
        }

        public static void main(String[] args) throws InterruptedException {
        Thread t1 = new TestThread("阿三");
        Thread t2 = new TestThread("李四");
        t1.start();
        t2.start();
        }
}
