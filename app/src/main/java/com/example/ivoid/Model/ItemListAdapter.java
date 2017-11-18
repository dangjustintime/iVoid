package com.example.ivoid.Model;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.constant.ItemListTags;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.api.endpoints.static_data.dto.*;
import net.rithms.riot.constant.Platform;

import java.util.Map;

/**
 * Created by Justin on 11/17/2017.
 */

public class ItemListAdapter {

    public static void main(String[] args) throws RiotApiException {
        ApiConfig config = new ApiConfig().setKey("RGAPI-8a25c8ed-87a7-473d-bab7-a0db87c835e2");
        RiotApi api = new RiotApi(config);

        // Get all items
        ItemList itemList = api.getDataItemList(Platform.NA, Locale.EN_US, null, ItemListTags.ALL);
        Map<String, net.rithms.riot.api.endpoints.static_data.dto.Item> map = itemList.getData();
    }
}
