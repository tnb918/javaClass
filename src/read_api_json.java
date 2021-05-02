import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Data{
    public String name;
    public int num;

    public Data(String name,int num){
        this.name=name;
        this.num=num;
    }
    public int getNum(){
        return num;
    }

    public String toString(){
        return "地区："+this.name+", 确诊人数："+this.num;
    }
}

public class read_api_json {
    public static String loadJson(String url) throws Exception {
        //读取url,返回json字符串
        StringBuilder json = new StringBuilder();
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(yc.getInputStream()));
        String inputLine = null;
        while((inputLine = in.readLine()) != null){
            json.append(inputLine);
        }
        in.close();

        return json.toString();
    }


    public static void main(String[] args) throws Exception {
        String url = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
        String json = loadJson(url);
        //输出json字符串
        System.out.println(json);

        int num;
        //将json字符串转换成jsonObject
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray china = jsonObject.getJSONObject("data")
                .getJSONArray("areaTree")
                .getJSONObject(0)
                .getJSONArray("children");

        num=china.size();
        String region_name;
        int confirm_num;

        List<Data> data = new ArrayList<Data>();
        for(int i=0;i<num;i++){
            //读取地区名称
            region_name=china.getJSONObject(i)
                    .getString("name");
            //读取该地区确诊人数
            confirm_num=Integer.parseInt(china.getJSONObject(i)
                    .getJSONObject("total")
                    .getString("confirm"));
            data.add(new Data(region_name,confirm_num));
        }

        data.sort(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                int num1 = o1.getNum();
                int num2 = o2.getNum();
                return num1 < num2 ? num1 : (num1 == num2) ? 0 : -1;
            }
        });
        System.out.println("确诊人数最高的五个区域如下：");
        for(int i=0;i<5;i++){
            System.out.println(data.get(i));
        }
    }
}
