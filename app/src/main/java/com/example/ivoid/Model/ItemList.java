package com.example.ivoid.Model;

import com.example.ivoid.Dto.ItemDto;
import com.example.ivoid.Dto.ItemTreeDto;

import java.util.Map;

/**
 * Created by Justin on 11/17/2017.
 */

public class ItemList {
    Map<String, ItemDto> data;
    public String version;
    ItemTreeDto tree[];
}
