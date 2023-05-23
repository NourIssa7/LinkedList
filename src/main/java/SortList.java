public abstract class SortList {

    public Entry head;
    int len;
   public int s_r=0 , s_c=0,f_r=0,f_c=0;
    public SortList() {
        this.head = new Entry(Integer.MIN_VALUE);
       this.head.next =new Entry(Integer.MAX_VALUE);
    }

    public  abstract  boolean add(Integer obj);
    public  abstract  boolean remove(Integer obj);
    public  abstract  boolean contain(Integer obj);
    public  abstract  int list_len();
    public  abstract  boolean is_sorted();


    public void printList(){
        Entry curr = this.head;
        while (curr != null){
            System.out.println(curr.object);
            curr = curr.next;

        }
    }
}
