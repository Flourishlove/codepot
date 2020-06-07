import java.io.*;
import java.util.*;


// 统计一下所有的 M×N(M为正类样本的数目，N为负类样本的数目)个正负样本对中，有多少个组中的正样本的score大于负样本的score
// 想象极端的情况，所有的正例都排在了最后，那么 sumRank-(M*(M+1)/2.0) 就等于0
class Solution{
    public double calAUC(double[] predict, int[] label){
        LinkedList<double[]> list = new LinkedList<>();
        int M = 0, N = 0;

        for(int i = 0; i < label.length; i++){
            list.add(new double[]{label[i], predict[i]});
            if(label[i] == 1) M++;
            else N++;
        }
        Collections.sort(list, new Comparator<double[]>(){
            public int compare(double[] a, double[] b){
                return Double.compare(a[1], b[1]);
            }
        });

        int sumRank = 0;
        for(int i = 0; i < label.length; i++){
            sumRank += list.get(i)[0] == 1 ? i+1 : 0;
        }

        return (sumRank - (M*(M+1)/2.0))/(M*N);
    }
}


public class CalAUC{
    public static void main(String args[]){
        Solution sol = new Solution();
        System.out.println(sol.calAUC(new double[]{0.1, 0.4, 0.35, 0.8}, new int[]{0, 0, 1, 1}));
    }
}

