package com.mydomain.tasks;

import junit.framework.Assert;

import org.junit.Test;

public class TicTacTest {

	@Test
	public void case01() {

		{
			int expected = 1;
			int actual = gameResultJudge(//
			new int[][] { //
					{ 1, 1, 1 },//
					{ 2, 1, 0 },//
					{ 2, 2, 0 } });
			Assert.assertEquals(expected, actual);
		}
		{
			int expected = 2;
			int actual = gameResultJudge(//
			new int[][] { //
					{ 1, 0, 1 },//
					{ 2, 2, 2 },//
					{ 1, 2, 0 } });
			Assert.assertEquals(expected, actual);
		}
		{
			int expected = 1;
			int actual = gameResultJudge(//
			new int[][] { //
					{ 0, 2, 2 },//
					{ 0, 2, 0 },//
					{ 1, 1, 1 } });
			Assert.assertEquals(expected, actual);
		}	
	}

	static int gameResultJudge(int[][] field) {
		if (field[0][0] != 0 && field[0][0] == field[1][0]&& field[0][0] == field[2][0]) return field[0][0]; // 横
		if (field[0][0] != 0 && field[0][0] == field[0][1] && field[0][0] == field[0][2]) return field[0][0]; // 縦
		if (field[0][0] != 0 && field[0][0] == field[1][1] && field[0][0] == field[2][2]) return field[0][0]; // 斜め

		if (field[0][1] != 0 && field[0][1] == field[1][1] && field[0][1] == field[2][1]) return field[0][1]; // 横2
		if (field[0][2] != 0 && field[0][2] == field[1][2] && field[0][2] == field[2][2]) return field[0][2]; // 横3

		if (field[1][0] != 0 && field[1][0] == field[1][1] && field[1][0] == field[1][2]) return field[1][0]; // 縦2
		if (field[2][0] != 0 && field[2][0] == field[2][1] && field[2][0] == field[2][2]) return field[2][0]; // 縦3

		return 0;
	}
}
