import java.util.Random;

public class RandomSeq {

    int seed;
    Random random;
    int maxNumber;

    public RandomSeq(int setseed ,int setmaxNum) {
        random = new Random(setseed);
        this.seed = setseed;
        this.maxNumber = setmaxNum;
    }
    public int next(){
        return random.nextInt(maxNumber);
    }
}
