import java.util.Arrays;
import java.util.Random;

/**
 * @author Casterx on 2020/5/29.
 */
public class Main {


    //每一个测试项与实际值的差值
    private static double getDiff(double[][] features,double[] results,double[] parameters,int index){
        double diff=0;
        for(int j=0;j<parameters.length-1;j++){
            diff+=parameters[j]*features[index][j];
        }
        diff+=parameters[parameters.length-1];
        diff-=results[index];
        return diff;
    }

    private  static double[] optimize(double[][] features, double[] results, double learningRate, double[] parameters){

        for (int i = 0; i < results.length; i++) {
            double diff=getDiff(features,results,parameters,i);
            for(int j=0;j<parameters.length;j++){
                double gradient;
                //常数项
                if(j==parameters.length-1){
                    gradient=diff;
                }
                //系数项
                else{
                    gradient=diff*features[i][j];
                }
                parameters[j]=parameters[j]-2* learningRate * gradient;
            }
        }
        return parameters;
    }


    private static void SGD(double[][] features, double[] results, double learningRate, double[] parameters) {
        parameters=optimize(features,results,learningRate,parameters);

        double totalLoss = 0;
        for (int j = 0; j < results.length; j++) {
            totalLoss = totalLoss + Math.pow((getDiff(features,results,parameters,j)), 2);
        }
        System.out.println(Arrays.toString(parameters));
        System.out.println("totalLoss:" + totalLoss);

    }




    public static void main(String[] args) {
        Random random = new Random();

//        double[] results = new double[100];
//        double[][] features = new double[100][3];
//        for (int i = 0; i < 100; i++) {
//            for (int j = 0; j < features[i].length; j++) {
//                features[i][j] = random.nextDouble();
//            }
//            results[i] = 3 * features[i][0] + 4 * features[i][1] + 5 * features[i][2] + 10;
//        }
//        double[] parameters = new double[] { 1.0, 1.0, 1.0, 1.0 };

        double[] results = new double[100];
        double[][] features = new double[100][1];
        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < features[i].length; j++) {
                features[i][j] = random.nextDouble();
            }
            results[i] = 0.41 * features[i][0]  -12.057;
        }
        double[] parameters = new double[] { 1.0, 1.0};


        double learningRate = 0.1;
        for (int i = 0; i < 30; i++) {
            Main.SGD(features, results, learningRate, parameters);
        }

    }
}
