package com.Monofraps.MonoBoxel;


import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;


/**
 * This chunk generator will generate a flat chunk.
 * 
 * @author Monofraps
 */
public class MBBoxelGenerator extends ChunkGenerator {
	
	private static final int	CHUNK_WIDTH		= 16;
	private static final int	CHUNK_LENGTH	= 16;
	private static final int	CHUNK_HEIGHT	= 128;					// does not work with 256 ?!
	private static final int	CHUNK_SIZE		= CHUNK_WIDTH * CHUNK_LENGTH
														* CHUNK_HEIGHT;
	
	private byte[]				flatChunk;
	private byte[]				borderChunk;
	private long				maxBoxelSize	= 16;
	private int					landHeight		= 6;
	
	public MBBoxelGenerator(long maxBoxelSize, boolean tmx) {
	
		flatChunk = new byte[CHUNK_SIZE];
		borderChunk = new byte[CHUNK_SIZE];
		
		this.maxBoxelSize = maxBoxelSize;
		
		if (!tmx) {
			for (int x = 0; x < CHUNK_WIDTH; x++) {
				for (int z = 0; z < CHUNK_LENGTH; z++) {
					flatChunk[xyzToByte(x, 0, z)] = (byte) Material.BEDROCK.getId();
				}
			}
			
			for (int x = 0; x < CHUNK_WIDTH; x++) {
				for (int z = 0; z < CHUNK_LENGTH; z++) {
					for (int y = 1; y < landHeight; y++) {
						flatChunk[xyzToByte(x, y, z)] = (byte) Material.DIRT.getId();
					}
				}
			}
			
			for (int x = 0; x < CHUNK_WIDTH; x++) {
				for (int z = 0; z < CHUNK_LENGTH; z++) {
					flatChunk[xyzToByte(x, landHeight, z)] = (byte) Material.GRASS.getId();
				}
			}
		} else {
			
			for (int x = 0; x < CHUNK_WIDTH; x++) {
				for (int z = 0; z < CHUNK_LENGTH; z++)
					for (int y = 0; y <= landHeight; y++) {
						flatChunk[xyzToByte(x, landHeight, z)] = (byte) Material.SNOW_BLOCK.getId();
					}
			}
			
			for (int x = 0; x < CHUNK_WIDTH; x++) {
				for (int z = (x % 2); z < CHUNK_LENGTH; z += 2)
					for (int y = 0; y <= landHeight; y++) {
						flatChunk[xyzToByte(x, landHeight, z)] = (byte) Material.OBSIDIAN.getId();
					}
			}
		}
		
		for (int i = 0; i < CHUNK_SIZE; i++)
			borderChunk[i] = (byte) Material.AIR.getId();
	}
	
	@Override
	public boolean canSpawn(World world, int x, int z) {
	
		// set the spawnpoint to the origin (0|0)
		if ((x == 0) && (z == 0))
			return true;
		
		return false;
	}
	
	/**
	 * Converts relative Chunk locations to 1 dimensional chunk index.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return 1 Dimensional Index for Chunk array's
	 */
	public int xyzToByte(int x, int y, int z) {
	
		return (x * CHUNK_WIDTH + z) * CHUNK_HEIGHT + y;
	}
	
	@Override
	public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
	
		// max-boxel-size set to 0 will result in non-limited worlds
		if (maxBoxelSize != 0) {
			if (chunkx > maxBoxelSize / 2)
				return Arrays.copyOf(borderChunk, borderChunk.length);
			if (chunkz > maxBoxelSize / 2)
				return Arrays.copyOf(borderChunk, borderChunk.length);
			if (chunkx < -maxBoxelSize / 2)
				return Arrays.copyOf(borderChunk, borderChunk.length);
			if (chunkz < -maxBoxelSize / 2)
				return Arrays.copyOf(borderChunk, borderChunk.length);
		}
		
		return Arrays.copyOf(flatChunk, flatChunk.length);
	}
	
}
