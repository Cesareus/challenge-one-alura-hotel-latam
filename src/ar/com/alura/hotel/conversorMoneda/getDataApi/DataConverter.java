
package ar.com.alura.hotel.conversorMoneda.getDataApi;
import java.io.*;
import okhttp3.*;
import org.json.JSONObject;



public class DataConverter {
        
        OkHttpClient client = new OkHttpClient();

        public JSONObject run(String url) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .addHeader("apikey", "DIOjMgYd1NUpo5gz1ILSRgBFOeKDDROy")
            .build();

        try (Response response = client.newCall(request).execute()) {            
             
            JSONObject dataJson =new JSONObject(response.body().string());
            return dataJson;
        }
   }
}


//
//    public static void main(String []args) throws IOException{
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
//
//        Request request = new Request.Builder()
//                .url("https://api.apilayer.com/currency_data/convert?to=USD&from=DOP&amount=1")
//                .addHeader("apikey", "ri83k1iqWgU3N09JGQ5Dfd2KWx2Ev8qw")
//                .method("GET", body)
//                .build();
//                
//                
//                
//                
//    Response response = client.newCall(request).execute();
//    System.out.println(response.body().string());
//    }