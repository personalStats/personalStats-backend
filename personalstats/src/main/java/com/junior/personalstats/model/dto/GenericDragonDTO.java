package com.junior.personalstats.model.dto;

import java.io.Serializable;

public class GenericDragonDTO implements Serializable {

	private long nuTotalDragons;
	private long nuGamesWithMorethen2Drags;
	private double nuGamesWithMorethen2DragsPerc;
	private long nuGamesWithMorethen2DragsAndWin;
	private double nuGamesWithMorethen2DragsAndWinPerc;
	private long nuGamesWithAtLeastOneDrag;
	private double nuGamesWithAtLeastOneDragPerc;
	private long nuGamesWinWithAtLeastOneDrag;
	private double nuGamesWinWithAtLeastOneDragPerc;
	private long nuDragonAvgPerGame;

	public long getNuTotalDragons() {
		return nuTotalDragons;
	}
	public void setNuTotalDragons(long nuTotalDragons) {
		this.nuTotalDragons = nuTotalDragons;
	}
	public long getNuGamesWithMorethen2Drags() {
		return nuGamesWithMorethen2Drags;
	}
	public void setNuGamesWithMorethen2Drags(long nuGamesWithMorethen2Drags) {
		this.nuGamesWithMorethen2Drags = nuGamesWithMorethen2Drags;
	}
	public double getNuGamesWithMorethen2DragsPerc() {
		return nuGamesWithMorethen2DragsPerc;
	}
	public void setNuGamesWithMorethen2DragsPerc(double nuGamesWithMorethen2DragsPerc) {
		this.nuGamesWithMorethen2DragsPerc = nuGamesWithMorethen2DragsPerc;
	}
	public long getNuGamesWithMorethen2DragsAndWin() {
		return nuGamesWithMorethen2DragsAndWin;
	}
	public void setNuGamesWithMorethen2DragsAndWin(long nuGamesWithMorethen2DragsAndWin) {
		this.nuGamesWithMorethen2DragsAndWin = nuGamesWithMorethen2DragsAndWin;
	}
	public double getNuGamesWithMorethen2DragsAndWinPerc() {
		return nuGamesWithMorethen2DragsAndWinPerc;
	}
	public void setNuGamesWithMorethen2DragsAndWinPerc(double nuGamesWithMorethen2DragsAndWinPerc) {
		this.nuGamesWithMorethen2DragsAndWinPerc = nuGamesWithMorethen2DragsAndWinPerc;
	}
	public long getNuGamesWithAtLeastOneDrag() {
		return nuGamesWithAtLeastOneDrag;
	}
	public void setNuGamesWithAtLeastOneDrag(long nuGamesWithAtLeastOneDrag) {
		this.nuGamesWithAtLeastOneDrag = nuGamesWithAtLeastOneDrag;
	}
	public double getNuGamesWithAtLeastOneDragPerc() {
		return nuGamesWithAtLeastOneDragPerc;
	}
	public void setNuGamesWithAtLeastOneDragPerc(double nuGamesWithAtLeastOneDragPerc) {
		this.nuGamesWithAtLeastOneDragPerc = nuGamesWithAtLeastOneDragPerc;
	}
	public long getNuGamesWinWithAtLeastOneDrag() {
		return nuGamesWinWithAtLeastOneDrag;
	}
	public void setNuGamesWinWithAtLeastOneDrag(long nuGamesWinWithAtLeastOneDrag) {
		this.nuGamesWinWithAtLeastOneDrag = nuGamesWinWithAtLeastOneDrag;
	}
	public double getNuGamesWinWithAtLeastOneDragPerc() {
		return nuGamesWinWithAtLeastOneDragPerc;
	}
	public void setNuGamesWinWithAtLeastOneDragPerc(double nuGamesWinWithAtLeastOneDragPerc) {
		this.nuGamesWinWithAtLeastOneDragPerc = nuGamesWinWithAtLeastOneDragPerc;
	}
	public long getNuDragonAvgPerGame() {
		return nuDragonAvgPerGame;
	}
	public void setNuDragonAvgPerGame(long nuDragonAvgPerGame) {
		this.nuDragonAvgPerGame = nuDragonAvgPerGame;
	}




}
