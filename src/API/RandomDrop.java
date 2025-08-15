package API;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RandomDrop {
	
	
	
	
	
	public static ItemStack randomDrop() {
		ItemStack EXP2 = new ItemStack(Material.EXP_BOTTLE, 2);
		ItemStack EXP4 = new ItemStack(Material.EXP_BOTTLE, 4);
		ItemStack EXP8 = new ItemStack(Material.EXP_BOTTLE, 8);
		ItemStack EXP16 = new ItemStack(Material.EXP_BOTTLE, 16);
		ItemStack apple = new ItemStack(Material.APPLE, 1);
		Random object = new Random();
		int i;
		for(int counter =1; counter<=1;counter++) {
		i = 1+object.nextInt(10); 
		if(i == 1) {
			return EXP2;
		}
		if(i == 2){
			return EXP2;
		}
		if(i == 3){
			return EXP4;
		}
		if(i == 4){
			return EXP4;
		}
		if(i == 5){
			return EXP8;
		}
		if(i == 6){

			return EXP8;
		}
		if(i == 7) {
			return EXP16;
		}
		if(i == 8) {
			return EXP16;
		}
		if(i == 9) {
			return EXP2;
		}
		if(i == 10) {
			return EXP2;
		}
		
		return EXP4;
		}
		return apple;
	}

}

