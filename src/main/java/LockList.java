import java.util.concurrent.locks.ReentrantLock;

public class LockList extends SortList {

    ReentrantLock lock = new ReentrantLock();

    public LockList() {
        super();
    }

    @Override
    public boolean add(Integer obj) {
        try {
            lock.lock();

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
                len = len +1;

                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(Integer obj) {
        try {
            lock.lock();
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
                f_r = f_r +1;
                return false;
            }
        } finally {
            lock.unlock();
        }

    }

    @Override
    public boolean contain(Integer obj) {
        try {
            lock.lock();
            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj) || prev.object.equals(obj)) {
                s_c = s_c +1;
                return true;
            } else {
                f_c= f_c +1;
                return false;
            }
        } finally {
            lock.unlock();
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
