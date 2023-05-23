public class SyncList extends SortList {

    public SyncList() {
        super();
    }
    @Override
    public synchronized boolean add(Integer obj) {
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
    }

    @Override
    public synchronized boolean remove(Integer obj) {
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

    }

    @Override
    public synchronized boolean contain(Integer obj) {
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
