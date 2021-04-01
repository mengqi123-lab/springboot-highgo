package com.hg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class HgdbTextApplicationTests /*implements Runnable*/{

    private int ticket = 10;
    private int number = 0;
    private final ReentrantLock lock = new ReentrantLock();
    //检查是否写入完成
    private boolean  writeComplete = false;

    /*@Test
    void contextLoads() {
        new Thread(() -> {
            for (int i=0;i<10;i++){
                System.out.println("第"+i+"次");
            }
        }).start();
    }*/

    /*@Test
    public void run(){
        while (true){
            if(ticket <= 0){
                break;
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticket--+"张票");
        }
    }

    public static void main(String[] args){
        HgdbTextApplicationTests threadTest = new HgdbTextApplicationTests();
        new Thread(threadTest,"A").start();
        new Thread(threadTest,"B").start();
        new Thread(threadTest,"C").start();
    }*/

    private void read(){
        synchronized (lock){
            while (!writeComplete){
                try{
                    lock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("number = " + number);
        }
    }

    private void write(int change) throws InterruptedException {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                number += change;
                lock.unlock();
            }else {
                System.out.println("一秒内没有获取到锁，不再等待");
            }
    }

    @Test
    public void test() throws InterruptedException {
        //开启一个线程 给 number + 100
        new Thread(() -> {
            writeComplete = false;
            for (int i=0;i<100;i++){
                try {
                    write(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            writeComplete = true;
            //System.out.println("number增加100");
            synchronized (lock){
                lock.notify();
            }
        }).start();

        //开启另一个线程 给 number — 100
        new Thread(() -> {
            for (int i=0;i<100;i++){
                //write(-1);
                read();
            }
            //System.out.println("number减少100");
        }).start();

        Thread.sleep(1000);
        //读取结果
    }

    @Test
    public void text2(){
        ArrayList<Integer> arrs = new ArrayList<>();
        arrs.add(11);
        arrs.add(10);
        arrs.add(12);
        arrs.add(13);
        arrs.add(11);
        arrs.add(15);
        arrs.add(11);
        arrs.add(17);
        arrs.add(18);
        arrs.add(16);

        arrs.stream().forEach(a -> System.out.println(a));

        for(int i=0;i<arrs.size();i++){
            for(int j=i+1;j<arrs.size();j++){
                if(arrs.get(i) == arrs.get(j)){
                    System.out.println(arrs.get(i));
                }
            }
        }
    }


    @Test
    public void HGDB(){
        Connection con = null;
        Statement sta = null;
        ResultSet res = null;
        PreparedStatement ps = null;
        try{
            Class.forName("com.highgo.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:highgo://192.168.80.141:5866/highgo","sysdba","High@123");
            String sql = " select name,age,sex from t_user order by age limit 6 offset 0 ";
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()){
                String name = res.getString("name");
                Integer age = res.getInt("age");
                Integer sex = res.getInt("sex");
                System.out.println(name + age + sex);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(res != null){res.close();}if(sta != null){sta.close();}if(con != null){con.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
