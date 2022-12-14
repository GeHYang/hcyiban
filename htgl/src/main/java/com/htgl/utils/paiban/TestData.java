package com.htgl.utils.paiban;

import java.util.HashMap;
import java.util.Map;

/**
 *  测试数据
 */
public class TestData {
    private static Map<String, String[]> map = new HashMap<>();
    static {
        map.put("2019102053",new String[]{"1,1,0,1,0","0,0,1,0,1","0,1,0,0,1","0,0,1,0,1","0,0,0,0,1","0,0,0,0,0"});
        map.put("2019120116",new String[]{"1,1,0,1,0","1,1,0,1,0","1,1,0,0,0","1,1,0,0,1","1,1,0,0,0","0,0,0,0,0"});
        map.put("2020100247",new String[]{"2,2,0,0,2","2,2,2,0,2","2,1,0,2,0","2,2,2,0,2","2,2,0,0,0","2,2,2,2,2"});
        map.put("2020100248",new String[]{"2,2,0,0,0","2,2,2,2,0","2,2,2,2,2","2,2,2,2,0","2,2,2,0,2","2,2,2,2,2"});
        map.put("2020100249",new String[]{"2,2,0,0,0","0,2,0,0,0","1,1,2,2,2","1,1,2,2,2","0,2,2,2,2","2,2,2,2,2"});
        map.put("2020100632",new String[]{"2,2,2,2,0","2,2,0,0,0","2,2,0,0,0","2,2,2,0,0","2,1,2,2,2","2,2,2,2,2"});
        map.put("2020101135",new String[]{"0,1,2,0,0","2,0,1,1,0","2,2,0,0,0","2,0,0,1,2","2,2,0,0,0","0,0,0,0,0"});
        map.put("2020101158",new String[]{"0,1,1,0,0","2,0,1,1,0","2,2,0,0,0","2,0,0,1,2","2,2,0,0,0","0,0,0,0,0"});
        map.put("2020101345",new String[]{"0,0,1,0,1","0,1,0,0,1","0,2,0,0,0","2,2,1,0,0","2,1,2,2,0","0,0,0,0,0"});
        map.put("2020101787",new String[]{"1,0,0,0,1","1,1,1,1,0","0,0,0,0,0","1,1,1,1,1","0,0,0,1,0","0,0,0,0,0"});
        map.put("2020101935",new String[]{"1,1,1,1,1","1,1,1,1,0","1,0,1,1,0","1,1,1,1,0","1,1,1,1,0","1,0,0,0,0"});
        map.put("2020102824",new String[]{"1,0,1,0,0","1,1,2,2,0","1,0,0,0,0","1,0,2,0,0","1,0,2,0,0","0,0,0,0,0"});
        map.put("2020103012",new String[]{"1,1,0,0,0","0,1,1,0,1","0,1,0,0,0","1,0,1,1,0","0,1,1,0,0","0,0,0,0,0"});
        map.put("2020103133",new String[]{"1,1,1,1,1","1,1,0,1,0","1,1,0,0,0","0,0,1,1,0","1,1,0,1,0","0,0,0,0,0"});
        map.put("2020103139",new String[]{"1,1,0,0,0","1,1,0,0,0","1,1,0,0,0","0,0,1,1,0","1,1,0,0,0","0,0,0,0,0"});
        map.put("2020103143",new String[]{"1,1,1,0,0","1,1,0,1,0","1,1,0,1,0","1,0,1,1,0","1,1,1,0,0","0,0,0,0,0"});
        map.put("2020103257",new String[]{"0,2,2,0,0","0,1,0,0,0","0,1,0,0,0","0,2,0,2,0","0,2,2,0,0","0,0,0,0,0"});
        map.put("2020103293",new String[]{"0,2,2,0,0","2,2,2,0,0","2,1,0,0,0","2,2,2,1,0","2,2,0,0,0","0,0,0,0,0"});
        map.put("2020120034",new String[]{"0,0,0,1,0","1,1,1,1,0","1,0,1,1,1","1,1,1,0,1","1,0,1,0,0","0,0,0,0,0"});
        map.put("2020120125",new String[]{"0,1,0,0,0","0,2,1,0,1","1,1,0,0,1","0,2,0,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2020120129",new String[]{"2,1,0,1,0","0,2,1,1,1","1,1,0,2,1","0,2,2,1,0","0,1,2,0,0","0,0,0,0,0"});
        map.put("2020120181",new String[]{"1,1,2,0,0","1,0,2,0,0","1,1,0,0,0","1,1,0,0,0","1,1,0,0,0","0,0,0,0,0"});
        map.put("2021100033",new String[]{"0,2,0,0,0","0,2,2,2,2","2,2,0,0,0","2,2,2,2,2","2,1,0,0,0","0,0,0,0,0"});
        map.put("2021100035",new String[]{"0,2,0,0,0","0,2,2,2,2","2,2,0,0,0","2,2,2,2,2","2,1,0,0,0","0,0,0,0,0"});
        map.put("2021100036",new String[]{"0,2,0,0,0","0,2,2,2,2","2,2,0,0,0","2,2,2,2,2","2,1,0,0,0","0,0,0,0,0"});
        map.put("2021100079",new String[]{"2,2,1,1,0","0,2,2,2,0","2,2,2,2,0","2,2,0,0,0","0,2,0,0,0","0,0,0,0,0"});
        map.put("2021100249",new String[]{"0,2,0,0,0","2,1,2,2,2","0,2,0,0,0","2,0,0,1,2","0,2,0,0,0","0,0,0,0,0"});
        map.put("2021100250",new String[]{"0,2,0,0,0","2,1,2,2,2","0,2,2,2,0","2,0,0,1,2","0,2,0,0,0","0,0,0,0,0"});
        map.put("2021100252",new String[]{"0,2,0,0,0","2,1,2,2,2","0,2,0,0,0","2,0,0,1,2","0,2,0,0,0","0,0,0,0,0"});
        map.put("2021100253",new String[]{"0,2,2,0,0","2,2,2,2,2","0,2,0,0,0","2,0,0,2,2","0,2,2,2,0","0,0,0,0,0"});
        map.put("2021100255",new String[]{"0,2,0,0,0","2,1,2,2,0","0,2,0,0,0","2,0,2,1,2","0,2,0,0,0","0,0,0,0,0"});
        map.put("2021100256",new String[]{"0,2,0,0,0","2,2,2,2,2","0,2,0,0,0","2,0,0,2,2","0,2,0,0,0","0,0,0,0,0"});
        map.put("2021100291",new String[]{"2,2,0,0,2","0,0,2,0,2","0,2,0,0,2","2,1,2,2,0","0,2,0,2,2","0,0,0,0,0"});
        map.put("2021100292",new String[]{"2,2,0,2,2","0,0,0,2,2","2,2,2,2,0","2,1,2,2,0","2,2,0,2,0","0,0,0,0,0"});
        map.put("2021100327",new String[]{"2,2,2,2,2","0,2,0,2,0","0,2,0,0,0","2,2,2,1,0","0,0,2,0,0","0,0,0,0,0"});
        map.put("2021100600",new String[]{"1,1,1,0,1","0,0,1,1,1","1,0,0,0,1","0,1,0,0,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021100606",new String[]{"1,1,1,0,1","0,0,1,2,1","1,0,0,0,1","0,1,0,0,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021100607",new String[]{"1,1,1,0,1","0,0,1,2,1","1,0,0,0,1","0,2,1,0,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021100726",new String[]{"2,2,2,0,2","2,2,2,2,2","2,0,0,0,2","2,2,2,0,0","2,2,2,0,0","0,0,0,0,0"});
        map.put("2021100728",new String[]{"2,2,2,0,2","2,2,2,2,2","2,0,0,0,2","2,2,2,0,0","2,2,2,0,0","0,0,0,0,0"});
        map.put("2021100732",new String[]{"2,2,2,0,2","2,2,2,2,2","2,0,0,0,2","2,2,2,0,0","2,2,2,0,0","0,0,0,0,0"});
        map.put("2021100741",new String[]{"2,2,2,0,2","2,2,2,2,2","2,0,0,0,2","2,2,2,0,0","2,2,2,2,0","0,0,0,0,0"});
        map.put("2021100900",new String[]{"0,2,2,0,0","2,2,2,0,0","2,2,0,0,0","0,1,1,1,0","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021100904",new String[]{"0,2,2,0,0","2,2,1,0,0","2,2,1,1,0","0,1,1,1,0","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021100924",new String[]{"0,2,2,0,0","2,2,2,0,0","2,2,0,0,0","0,1,1,1,0","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021100926",new String[]{"0,2,2,0,1","2,2,2,0,1","2,2,0,0,1","0,1,1,1,1","2,2,0,0,1","0,0,0,0,0"});
        map.put("2021100935",new String[]{"0,2,2,0,0","2,2,0,0,0","2,2,1,1,0","0,1,1,1,0","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021100936",new String[]{"0,2,2,0,0","2,2,1,0,0","2,2,0,0,0","0,1,1,1,0","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021100951",new String[]{"1,1,1,1,1","1,1,1,1,1","0,1,0,0,1","1,1,1,1,1","0,1,0,1,1","0,0,0,0,0"});
        map.put("2021100973",new String[]{"1,1,2,0,0","2,1,1,1,0","0,1,0,0,1","2,2,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021100979",new String[]{"1,1,2,0,0","2,1,1,1,0","0,1,0,0,1","2,2,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021100987",new String[]{"1,1,2,0,0","2,1,1,1,0","0,1,0,0,1","2,2,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021101001",new String[]{"1,1,2,0,0","0,1,1,1,0","0,1,1,0,1","2,2,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021101125",new String[]{"1,1,0,0,2","2,2,2,0,0","1,1,0,0,1","0,2,0,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021101132",new String[]{"1,1,0,0,2","1,1,2,0,0","1,1,0,0,1","0,2,0,0,0","0,2,1,1,0","0,0,0,0,0"});
        map.put("2021101161",new String[]{"0,2,0,0,0","0,2,2,0,2","0,2,0,0,2","2,2,0,0,1","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021101168",new String[]{"0,2,0,0,0","0,2,2,0,2","0,2,0,0,2","2,2,0,0,2","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021101175",new String[]{"0,2,0,0,0","0,1,2,0,2","0,1,0,0,2","2,2,0,0,1","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021101177",new String[]{"0,2,0,0,0","0,2,2,0,2","0,2,0,0,2","2,2,0,0,1","2,2,0,0,0","0,0,0,0,0"});
        map.put("2021101204",new String[]{"0,0,2,0,0","0,2,2,0,2","0,0,2,2,0","2,2,2,2,1","2,2,2,2,0","0,0,0,0,0"});
        map.put("2021101229",new String[]{"2,2,2,2,0","0,2,2,0,2","2,0,2,2,0","2,2,2,2,2","2,2,2,2,0","0,0,0,0,0"});
        map.put("2021101236",new String[]{"0,0,2,0,0","0,2,2,0,2","0,1,0,0,0","2,2,2,0,2","2,2,2,0,1","0,0,0,0,0"});
        map.put("2021101281",new String[]{"2,2,0,0,2","0,2,0,2,0","2,2,0,0,2","0,1,0,2,2","0,1,1,0,0","0,0,0,0,0"});
        map.put("2021101306",new String[]{"2,2,0,0,2","0,2,0,2,0","2,2,0,0,2","0,1,0,2,0","0,2,2,0,0","0,2,2,0,0"});
        map.put("2021101332",new String[]{"1,1,1,0,1","0,1,1,0,1","1,1,0,0,1","0,1,1,1,1","1,0,0,0,1","0,0,0,0,0"});
        map.put("2021101342",new String[]{"2,2,0,0,2","0,2,0,2,0","2,0,0,2,2","0,1,0,2,0","0,2,0,2,0","0,2,2,0,2"});
        map.put("2021101388",new String[]{"0,1,2,0,0","2,2,0,2,0","2,2,0,0,0","0,2,2,2,0","0,2,2,2,0","0,0,0,0,0"});
        map.put("2021101476",new String[]{"0,2,1,1,0","2,2,1,1,0","0,1,0,0,2","0,0,0,2,1","0,2,0,0,0","0,0,0,0,0"});
        map.put("2021101652",new String[]{"0,1,1,1,0","1,1,0,1,0","1,1,1,1,0","1,1,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021101671",new String[]{"0,2,0,2,2","0,2,2,2,2","2,0,2,2,0","0,2,2,2,0","1,2,0,0,0","0,0,0,0,0"});
        map.put("2021101680",new String[]{"0,2,0,2,2","0,2,2,2,2","2,0,2,2,0","2,2,0,0,0","1,2,0,0,0","0,0,0,0,0"});
        map.put("2021101747",new String[]{"1,0,2,2,0","2,0,2,2,2","0,0,0,0,0","0,2,2,2,1","1,2,0,0,0","0,0,0,0,0"});
        map.put("2021101749",new String[]{"1,0,0,0,0","0,2,0,0,2","0,2,0,0,0","0,2,2,2,1","1,2,1,1,0","0,0,0,0,0"});
        map.put("2021101750",new String[]{"1,0,1,1,0","2,0,2,2,2","0,2,0,0,0","0,2,2,2,2","2,2,1,1,0","0,0,0,0,0"});
        map.put("2021101752",new String[]{"1,0,2,2,0","2,0,2,2,2","0,2,0,0,0","0,2,2,2,1","1,2,1,1,0","0,0,0,0,0"});
        map.put("2021101775",new String[]{"1,0,2,2,0","0,0,1,1,2","0,2,0,0,0","2,2,2,2,2","1,2,2,2,0","0,0,0,0,0"});
        map.put("2021101787",new String[]{"1,0,2,2,0","0,0,1,1,2","0,2,0,0,0","2,2,2,2,1","1,2,2,2,0","0,0,0,0,0"});
        map.put("2021101797",new String[]{"1,0,2,2,0","2,0,2,2,2","2,2,0,0,0","2,2,2,2,1","1,2,2,2,0","0,0,0,0,0"});
        map.put("2021101807",new String[]{"1,0,2,2,0","0,0,1,1,2","0,2,0,0,0","2,2,2,1,1","1,2,2,2,0","0,0,0,0,0"});
        map.put("2021101935",new String[]{"1,1,1,1,1","2,2,2,2,0","1,1,0,0,1","2,2,2,2,2","2,2,2,2,0","0,0,0,0,0"});
        map.put("2021102013",new String[]{"0,1,1,1,1","0,1,0,1,0","1,1,0,0,0","1,1,2,0,0","1,0,1,0,0","0,0,0,0,0"});
        map.put("2021102100",new String[]{"0,1,0,0,1","0,1,1,0,1","0,1,1,1,1","1,1,1,0,0","1,1,1,0,0","0,0,0,0,0"});
        map.put("2021102137",new String[]{"0,0,1,1,0","1,1,1,0,1","0,1,0,0,0","1,1,1,0,1","0,0,1,1,0","0,0,0,0,0"});
        map.put("2021102171",new String[]{"0,0,1,1,1","1,1,0,1,1","0,1,1,1,1","1,1,1,0,1","0,0,1,1,1","0,0,0,0,0"});
        map.put("2021102425",new String[]{"1,1,1,1,1","1,1,1,1,0","1,1,1,1,0","1,1,1,1,0","1,1,1,1,0","1,1,1,1,0"});
        map.put("2021103328",new String[]{"0,2,0,0,0","2,2,0,1,0","0,2,0,0,0","2,0,2,2,0","2,2,2,0,0","0,0,0,0,0"});
        map.put("2021103376",new String[]{"1,1,1,0,0","0,1,1,1,0","1,1,0,0,0","0,1,1,1,1","1,0,0,0,0","0,0,0,0,0"});
        map.put("2021103388",new String[]{"1,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,1","1,0,0,0,0","0,0,0,0,0"});
        map.put("2021103575",new String[]{"1,1,1,0,1","0,1,0,0,1","1,1,0,0,1","0,1,1,0,1","1,1,0,0,0","0,0,0,0,0"});
        map.put("2021120020",new String[]{"0,1,0,1,0","1,0,0,1,0","0,1,0,0,0","1,1,1,1,0","1,0,1,0,0","0,0,0,0,0"});
        map.put("2021120026",new String[]{"0,1,0,1,0","1,0,0,1,0","0,1,0,0,0","1,1,1,1,0","1,0,1,0,0","0,0,0,0,0"});
        map.put("2021120042",new String[]{"0,1,1,0,0","0,1,0,1,0","0,1,0,0,0","1,1,1,1,0","1,0,1,0,0","0,0,0,0,0"});
        map.put("2021120056",new String[]{"0,1,1,0,0","0,1,0,1,0","0,1,0,0,0","1,1,1,1,0","1,0,1,0,0","0,0,0,0,0"});
        map.put("2021120076",new String[]{"0,1,1,0,0","0,1,0,1,0","0,1,0,0,0","1,1,1,1,0","1,0,1,0,0","0,0,0,0,0"});
        map.put("2021120096",new String[]{"0,1,2,0,0","1,1,1,0,0","0,1,0,0,0","1,1,0,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120097",new String[]{"0,1,1,0,0","1,1,1,1,0","0,1,0,0,0","1,1,0,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120099",new String[]{"0,1,1,0,0","1,1,1,0,0","0,1,1,1,0","1,1,1,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120103",new String[]{"0,1,1,0,0","1,1,1,0,0","1,1,1,1,0","1,1,0,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120105",new String[]{"0,1,2,0,0","1,1,1,0,0","0,1,0,0,0","1,1,0,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120109",new String[]{"0,1,1,0,0","1,1,1,1,0","0,1,0,0,0","1,1,0,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120110",new String[]{"0,1,2,0,0","1,1,1,0,0","0,1,0,0,0","1,1,0,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120118",new String[]{"0,1,1,0,0","1,1,1,0,0","0,1,0,0,0","1,1,0,0,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120124",new String[]{"0,1,1,1,0","1,1,1,1,0","0,1,1,0,0","1,1,0,0,0","1,1,1,1,1","0,0,0,0,0"});
        map.put("2021120129",new String[]{"0,1,1,2,0","1,1,1,1,0","0,1,0,2,2","1,1,0,0,0","0,1,1,1,1","0,0,0,0,0"});
        map.put("2021120167",new String[]{"0,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,0","0,0,1,1,0","0,0,0,0,0"});
        map.put("2021120169",new String[]{"0,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,0","0,0,1,1,0","0,0,0,0,0"});
        map.put("2021120170",new String[]{"0,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021120186",new String[]{"1,1,1,0,0","0,1,1,1,0","1,1,0,0,0","0,1,1,1,0","0,1,1,1,0","0,0,0,0,0"});
        map.put("2021120189",new String[]{"0,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021120207",new String[]{"0,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021120211",new String[]{"0,1,1,1,1","0,1,1,1,1","1,0,1,1,1","0,1,1,1,1","0,1,0,0,1","0,0,0,0,0"});
        map.put("2021120213",new String[]{"0,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021120214",new String[]{"0,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,0","0,1,0,0,0","0,0,0,0,0"});
        map.put("2021120217",new String[]{"0,1,1,0,0","0,1,1,1,0","1,0,0,0,0","0,1,1,1,0","0,1,0,0,0","0,0,0,0,0"});
    }

    public static Map<String, String[]> getMap() {
        return map;
    }
}
