import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockList extends SortList {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public RWLockList() {
        super();
    }

    @Override
    public boolean add(Integer obj) {
        try {
            lock.writeLock().lock();

            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj) || prev.object.equals(obj)) {
                return false;
            } else {
                Entry newEntry = new Entry(obj);
                newEntry.next = curr;
                prev.next = newEntry;
                len = len+1;
                return true;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean remove(Integer obj) {
        try {
            lock.writeLock().lock();
            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj)) {
                prev.next = curr.next;
                len = len-1;
                s_r +=1;
                return true;
            } else {
                f_r +=1;
                return false;
            }
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public boolean contain(Integer obj) {
        try {
            lock.readLock().lock();
            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj) || prev.object.equals(obj)) {
                s_c +=1;
                return true;
            } else {
                f_c +=1;
                return false;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public int list_len() {
        return len;
    }

    @Override
    public boolean is_sorted() {
        Entry prev = this.head;
        Entry curr = prev.next;
        Entry next = curr.next;

        while ( next !=null) {
            if(curr.object.compareTo(next.object) >0 )
                return false;

            curr = next;
            next = curr.next;

        }
        return true;

    }


}
