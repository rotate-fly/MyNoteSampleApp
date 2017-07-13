package com.huangzisheng.com.notesample.application.manage;

import com.huangzisheng.com.notesample.activity.ShapeAcitivty;

import java.util.HashMap;

/**
 * Created by huangzisheng on 2017/7/2.
 */

public class AcitivityMapping {
    private static AcitivityMapping activityMapping;
    private static HashMap<String, ActivityStruct> activityMap = new HashMap<String, ActivityStruct>();


    //偶有出现两次new ActivityMapping现象，需要同步处理，否则会导致从trade_function自动生成的查询类挂机。
    //add buy qiyaping
    public static AcitivityMapping getInstance() {
        if (activityMapping == null) {
            synchronized (AcitivityMapping.class)
            {
                if(activityMapping == null) {
                    activityMapping = new AcitivityMapping();
                }
            }
        }
        return activityMapping;
    }

    private  AcitivityMapping() {
//        activityMap.put(HsActivityId.PERSON_CENTERNEW, new ActivityStruct("个人中心", NewPersonCentreActivity.class));
        activityMap.put(HsActivityId.SHAPE, new ActivityStruct("shape标签详解", ShapeAcitivty.class));

    }

    public ActivityStruct getAcitiActivityStruct(String activityId) {
        if (activityMap.containsKey(activityId)) {
            return activityMap.get(activityId);
        } else {
            return null;
        }
    }
}
