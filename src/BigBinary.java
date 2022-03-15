public class BigBinary {
    private int[] bits;
    private boolean positive;

    public BigBinary(int[] bits, boolean isPositive){
        this.bits=bits;
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
        if (bigbinary.toString().equals("0")) return this;
        else if (positive&& bigbinary.positive||!positive&&!bigbinary.positive){
            int length=Math.max(bits.length,bigbinary.bits.length);
            int[] b_bits=new int[length];
            int[] original_bits=new int[length];
            if (bigbinary.bits.length<length){
                System.arraycopy(bigbinary.bits,0,b_bits,length-bigbinary.bits.length,bigbinary.bits.length);
                original_bits=bits;
            }else{
                System.arraycopy(bits,0,original_bits,length-bits.length,bits.length);
                b_bits=bigbinary.bits;
            }

            int carry=0;
            StringBuilder sb= new StringBuilder();
            for (int i=length-1;i>=0;i--){
                int current=b_bits[i]+original_bits[i]+carry;
                if (current<2){
                    sb.insert(0, current);
                    carry=0;
                }else {
                    carry=1;
                    sb.insert(0,current-2);
                }
            }
            if (carry==1){
                sb.insert(0,carry);
            }

            String [] strings=sb.toString().split("");
            int[] newBits=new int[strings.length];
            for (int j = 0; j < strings.length; j++) {
                newBits[j]=Integer.parseInt(strings[j]);
            }


            bits=newBits;
            return new BigBinary(newBits,positive);

        }else {
            return minus(new BigBinary(bigbinary.bits, !bigbinary.positive));
        }

    }


    public BigBinary minus(BigBinary bigbinary){
        if (bigbinary.toString().equals("0")) return this;
        else if (positive&& bigbinary.positive||!positive&&!bigbinary.positive){
            int length;
            int[] small_bits;
            int[] big_bits;
            if (compareBigger(this,bigbinary)){
                length=bits.length;
               small_bits=new int[length];
                bigbinary.bits=convert(bigbinary.toString().split(""));
                System.arraycopy(bigbinary.bits,0, small_bits,length-bigbinary.bits.length,bigbinary.bits.length);
                big_bits=bits;
            }else {
                length=bigbinary.bits.length;
                small_bits=new int[length];
                bits=convert(this.toString().split(""));
                System.arraycopy(bits,0, small_bits,length-bits.length,bits.length);
                big_bits =bigbinary.bits;
                positive=!positive;
            }

            int carry=0;
            StringBuilder sb= new StringBuilder();
            for (int i=length-1;i>=0;i--){
                int currrent=big_bits[i]- small_bits[i]-carry;
                if (currrent<0){
                    sb.insert(0,currrent+2);
                    carry=1;
                }else {
                    sb.insert(0,currrent);
                    carry=0;
                }
            }

            String [] strings=sb.toString().split("");
            int[] newBits=new int[strings.length];
            for (int j = 0; j < strings.length; j++) {
                newBits[j]=Integer.parseInt(strings[j]);
            }
            bits=newBits;

                return new BigBinary(newBits,positive);


        }else {
            return add(new BigBinary(bigbinary.bits,!bigbinary.positive));
        }
    }


    public BigBinary multiply(BigBinary bigbinary){
        if (bigbinary.toString().equals("0")){
            bits=new int[]{0};
            return new BigBinary(bits,true);
        }
        int[] bit=convert(this.toString().split(""));
        int[] b_bit=convert(bigbinary.toString().split(""));
        int length=bit.length+b_bit.length;
        int[] fin=new int[length];
        int index=0;
        for (int i= b_bit.length-1;i>=0;i--){
            if (b_bit[i]==0){
                index++;
                continue;
            }
            else {
                for (int j =0 ; j <bit.length ; j++) {

                    fin[length-1-index-j]+=bit[bit.length-1-j];
                }
                index++;
            }
        }
        int carry=0;
        for (int i = length-1; i >=0 ; i--) {
            int temp=carry+fin[i];
            if (temp<2){
                fin[i]=temp;
                carry=0;
            }
            else {
                carry=temp/2;
                fin[i]=temp%2;
            }
        }
        bits=fin;
        boolean isPositive=positive&& bigbinary.positive||!positive&&!bigbinary.positive;
        positive=isPositive;
        return new BigBinary(fin,isPositive);

    }

    //bits>bit2 true else false
    public boolean compareBigger(BigBinary b1,BigBinary b2){
        int[] bits1=convert(b1.toString().split(""));
        int[] bits2=convert(b2.toString().split(""));
        if (bits1.length> bits2.length)return true;
        else if (bits1.length<bits2.length)return false;
        else {
            for (int i = 0; i < bits1.length; i++) {
                if (bits1[i]>bits2[i]){
                    return true;
                }else if (bits1[i]<bits2[i]){
                    return false;
                }
            }
        }
        return false;
    }

    //删去bits前面多余的0
    public int[] convert(String[] strings){
        int[] newBits;
        if (strings[0].equals("-")){
            newBits=new int[strings.length-1];
            for (int j = 0; j < strings.length-1; j++) {
                newBits[j]=Integer.parseInt(strings[j+1]);
            }
        }else {
            newBits = new int[strings.length];
            for (int j = 0; j < strings.length; j++) {
                newBits[j] = Integer.parseInt(strings[j]);
            }
        }
        return newBits;
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
