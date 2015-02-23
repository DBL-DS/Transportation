package lab;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Training extends JFrame implements Runnable, ActionListener
{
    public static JButton run;
    public static JButton exit;
    public static JButton stop;
    public static Thread demo;
    //public static Thread demo2;
    public static int i = 1,j=1;
    private boolean waitFlag=false;        //设置一标志，false暂停，true运行。
    //用到两个过时方法。还不知道怎么解决
    public void run()
    {
        while(true)
        {
            System.out.println(""+Thread.currentThread().getName()+"  "+i++);
            try
            {
                //demo.sleep(1000);
                Thread.sleep(1000);//静态方法。
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            while(!waitFlag)
            {
                System.out.println(Thread.currentThread().getName()+ " is pausing!");
                pause();
                System.out.println(Thread.currentThread().getName()+" is continuing!");
            }
        }
    }

    public     synchronized void pause()
    {
        try
        {
            wait();
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
    public synchronized void awake()
    {
        notifyAll();//用notify()只会唤醒一个等待线程
    }
    public static void main(String[] args)
    {
        Training he = new Training();
        demo = new Thread(he, "线程_1");
        //demo2 = new Thread(he,"线程_2");
        demo.start();
        //demo2.start();

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.CENTER));
        run = new JButton("运行");
        exit = new JButton("退出");
        stop = new JButton("暂停");

        run.addActionListener(he);
        exit.addActionListener(he);
        stop.addActionListener(he);

        jp.add(run);
        jp.add(stop);
        jp.add(exit);
        he.setLayout(new FlowLayout(FlowLayout.CENTER));
        he.add(jp);
        he.setLocation(300, 200);
        he.setSize(200, 100);
        he.setTitle("测试");
        he.setVisible(true);
    }

    public void actionPerformed(ActionEvent b)
    {
        if (b.getSource() == run)
        {
            //System.out.println("线程正在运行");
            waitFlag=true;
            awake();
            System.out.println("线程_1 的状态是 "+demo.getState());
            //System.out.println("线程_2 的状态是 "+demo2.getState());
        }
        else if (b.getSource() == stop)
        {
            waitFlag=false;
            System.out.println("线程_1 的状态是 "+demo.getState());
            //System.out.println("线程_2 的状态是 "+demo2.getState());
        }
        else if (b.getSource() == exit)
        {
            System.out.println("退出");
            System.out.println(demo.activeCount());
            System.exit(0);
            return;
        }
    }
}
