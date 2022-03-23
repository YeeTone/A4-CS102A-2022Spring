public class BigBinary {
    private int[] bits;
    private boolean positive;

    public BigBinary(int[] bits, boolean isPositive){
        this.bits=bits.clone();
        this.positive=isPositive;
    }

    @Override
    public String toString() {
        int i=0;
        StringBuilder sb = new StringBuilder();
        while (i<bits.length){
            if (bits[i]!=0){
                break;
            }
            i++;
        }
        if (i== bits.length) return "0";
        for (; i < bits.length ; i++) {
            sb.append(bits[i]);
        }
        if (positive) return sb.toString();
        else return "-"+sb;

    }

    public BigBinary add(BigBinary bigbinary){
        if (positive&& bigbinary.positive||!positive&&!bigbinary.positive) {
            int[] b_bit = bigbinary.bits;
            int[] fin = new int[Math.max(b_bit.length, bits.length) + 1];
            int i = 0;
            for (; i < Math.min(b_bit.length, bits.length); i++) {
                fin[fin.length - 1 - i] = b_bit[b_bit.length - 1 - i] + bits[bits.length - i - 1];
            }
            for (; i <b_bit.length ; i++) {
                fin[fin.length-1-i]=b_bit[b_bit.length-1-i];
            }
            for (;i< bits.length;i++){
                fin[fin.length-1-i]=bits[bits.length-1-i];
            }
            int carry = 0;
            for (int j = fin.length - 1; j >= 0; j--) {
                int temp = carry + fin[j];
                carry = temp / 2;
                fin[j] = temp % 2;
            }
            this.bits = fin;
            return new BigBinary(fin,this.positive);
        }else {
            return this.minus(new BigBinary(bigbinary.bits,!bigbinary.positive));
        }

    }



    public BigBinary minus(BigBinary bigbinary){
       if (positive&& bigbinary.positive||!positive&&!bigbinary.positive){
           int[] small_bits;
           int[] great_bits;
           boolean isPositive;
           if(compareBigger(this,bigbinary)){
               small_bits=bigbinary.bits;
               great_bits=bits;
               isPositive=this.positive;
           }else {
               small_bits=bits;
               great_bits=bigbinary.bits;
               isPositive=!this.positive;
           }

           int[] fin = new int[Math.max(small_bits.length, great_bits.length)];
           int i = 0;
           for (; i < Math.min(small_bits.length,great_bits.length); i++) {
               fin[fin.length - 1 - i] =  great_bits[great_bits.length - i - 1]- small_bits[small_bits.length - 1 - i];
           }
           for (; i <small_bits.length ; i++) {
               fin[fin.length-1-i]=small_bits[small_bits.length-1-i];
           }
           for (;i< great_bits.length;i++){
               fin[fin.length-1-i]=great_bits[great_bits.length-1-i];
           }
           int carry = 0;
           for (int j = fin.length - 1; j >= 0; j--) {
               int temp =   fin[j]-carry;
               if (temp<0) {
                   carry = 1;
                   fin[j] = 2+temp;
               }else {
                   fin[j]=temp;
                   carry=0;
               }
           }
           this.bits = fin;
           this.positive=isPositive;
           return new BigBinary(fin,isPositive);

        }else {
            return this.add(new BigBinary(bigbinary.bits,!bigbinary.positive));
        }
    }


    //将二进制转为256进制 取八位变一位
    public static int[] conversion2_256(int[] A){
        int[] B=new int[A.length/8+1];
        int index=0;
        if (A.length/8>0) {
            for (int i = 7; i < A.length; i += 8) {
                for (int j = 0; j <8; j++) {
                    B[B.length - index - 1] = A[A.length - 1 - i + j] + B[B.length - index - 1] * 2;
                }
                index++;
            }
        }
        for (int i = 0; i < A.length%8; i++) {
            B[B.length-index-1]=A[i]+B[B.length-index-1]*2;
        }
        return B;

    }

    //将256进制转化为二进制，取一位变八位
    public static int[] conversion256_2(int[] A){
        int[] B=new int[A.length*8];
        for (int i = 0; i <A.length; i++) {
            for (int j = 0; j < 8; j++) {
                B[B.length-8*i-j-1]=A[A.length-i-1]%2;
                A[A.length-1-i]=A[A.length-i-1]/2;
            }
        }
        return B;
    }


        public BigBinary multiply(BigBinary bigbinary){
        if (bigbinary.toString().equals("0")){
            bits=new int[]{0};
            return new BigBinary(bits,true);
        }
        int[] bit=conversion2_256(this.bits);
        int[] b_bit=conversion2_256(bigbinary.bits);
        int length=bit.length+b_bit.length;
        int[] fin=new int[length];
        int index=0;
        for (int i= b_bit.length-1;i>=0;i--){
            if (b_bit[i]==0){
                index++;
            }
            else {
                for (int j =0 ; j <bit.length ; j++) {

                    fin[length-1-index-j]+=bit[bit.length-1-j]*b_bit[i];
                }
                index++;
            }
        }
        int carry=0;
        for (int i = length-1; i >=0 ; i--) {
            int temp=carry+fin[i];
                carry=temp/256;
                fin[i]=temp%256;
        }
        int[]ans=conversion256_2(fin);
        this.bits=ans;
        boolean isPositive=positive&& bigbinary.positive||!positive&&!bigbinary.positive;
        positive=isPositive;
        return new BigBinary(ans,isPositive);

    }


    //bits>bit2 true else false
    public boolean compareBigger(BigBinary b1,BigBinary b2){
        if (b1.toString().length()> b2.toString().length())return true;
        else if (b1.toString().length()<b2.toString().length())return false;
        else {
            String[] bits1=b1.toString().split("");
            String[] bits2=b2.toString().split("");
            int i=b1.positive?0:1;
            for (; i <bits1.length; i++) {
                if (Integer.parseInt(bits1[i])>Integer.parseInt(bits2[i])){
                    return true;
                }else if (Integer.parseInt(bits1[i])<Integer.parseInt(bits2[i])){
                    return false;
                }
            }
        }
        return true;
    }


    public static BigBinary add(BigBinary b1, BigBinary b2){
        BigBinary bigBinary=new BigBinary(b1.bits, b1.positive);
        return bigBinary.add(b2);
    }
    public static BigBinary minus(BigBinary b1, BigBinary b2){
        BigBinary bigBinary=new BigBinary(b1.bits, b1.positive);
        return bigBinary.minus(b2);
    }
    public static BigBinary multiply(BigBinary b1, BigBinary b2){
        BigBinary bigBinary=new BigBinary(b1.bits, b1.positive);
        return bigBinary.multiply(b2);
    }

}
