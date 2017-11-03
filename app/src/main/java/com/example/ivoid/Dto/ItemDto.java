/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ivoid.Dto;

import java.util.Map;

/**
 *
 * @author BoCameron
 */
public class ItemDto {
		GoldDto gold;
		String plaintext;
		boolean hideFromAll;
		boolean inStore;
		String into[];
		int id;
		InventoryDataStatsDto stats;
		String colloq;
		Map<String, Boolean> maps;
		int specialRecipe;
		GoldDto image;
		String description;
		String tags[];
		Map<String,String> effect;
		String requiredChampion;
		String from[];
		String group;
		boolean consumeOnFull;
		String name;
		boolean consumed;
		String sanitizedDescription;
		int depth;
		int stacks;	
	
	public ItemDto() {
		
	}

}
