import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class SyncListTest extends TestCase {

    public void testAddList(){

        SyncList syncList = new SyncList();
//        syncList.remove(Integer.MAX_VALUE);
        syncList.add(1);
        syncList.add(2);
        syncList.add(3);
        syncList.add(Integer.MIN_VALUE);
        syncList.add(3);
        System.out.println(syncList.contain(5));
        System.out.println(syncList.contain(2));
        syncList.remove(3);
    }

    public void testRandSeq() {
        RandomSeq randomSeq = new RandomSeq(0, 80_000);
        for (int i = 0; i < 10; i++) {
            System.out.print(randomSeq.next() + " ");
        }
    }

    int randLen = 80_000;
   public void testHelp(SortList list, String label) {
        RandomSeq seq = new RandomSeq(0, 80_000);
        List<Thread> addThreads = new ArrayList<>();
        List<Thread> containThreads = new ArrayList<>();
        List<Thread> removeThreads = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            AddThread addThread = new AddThread(seq, randLen / 8, list);
            ContainThread containThread = new ContainThread(seq, randLen / 8, list);
            RemoveThread removeThread = new RemoveThread(seq, randLen / 8, list);
            Thread threadA = new Thread(addThread);
            addThreads.add(threadA);
            Thread threadC = new Thread(containThread);
            containThreads.add(threadC);
            Thread threadR = new Thread(removeThread);
            removeThreads.add(threadR);
        }

        long startA = System.currentTimeMillis();

        addThreads.stream().forEach(e -> e.start() );
        addThreads.stream().forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        long endA = System.currentTimeMillis() - startA;

       System.out.println("ADD "+label+" execution task: "+endA+" ms");
       System.out.println("ADD "+label+"  list length "+ list.list_len());
       if(list.is_sorted()) {
           System.out.println("Add list is sorted ");
       }else{
           System.out.println("Add list is not sorted ");
       }
       long startC = System.currentTimeMillis();

       containThreads.stream().forEach(e -> e.start() );
       containThreads.stream().forEach(e -> {
           try {
               e.join();
           } catch (InterruptedException ex) {
               throw new RuntimeException(ex);
           }
       });
       long endC = System.currentTimeMillis() - startC;
       System.out.println("Contain "+label+" execution task: "+endC+" ms");
       System.out.println("Contain "+label+"  contain success "+ list.s_c);
       System.out.println("Contain "+label+"  contain failure "+ list.f_c);

       long startR = System.currentTimeMillis();

       removeThreads.stream().forEach(e -> e.start() );
       removeThreads.stream().forEach(e -> {
           try {
               e.join();
           } catch (InterruptedException ex) {
               throw new RuntimeException(ex);
           }
       });
       long endR = System.currentTimeMillis() - startR;


       System.out.println("Remove "+label+" execution task: "+endR+" ms");

       System.out.println("Remove "+label+"  list length "+ list.list_len());
       if(list.is_sorted()) {
           System.out.println("Remove list is sorted ");
       }else{
           System.out.println("Remove list is not sorted! ");
       }
       System.out.println("Remove "+label+"  remove success "+ list.s_r);
       System.out.println("Remove "+label+"  remove failure "+ list.f_r);
   }

    public void testRun(){
        SyncList syncList = new SyncList();
        testHelp(syncList,"Synchronization");
        System.out.println("==============");
        RWLockList rwLockList = new RWLockList();
        testHelp(rwLockList, "RWLock");
        System.out.println("==============");
        LockList list = new LockList();
        testHelp(list,"Lock");
    }
}
