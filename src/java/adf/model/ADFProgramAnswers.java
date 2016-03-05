/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model;

import adf.model.ExerciseAnswers.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author miel
 */
public class ADFProgramAnswers implements Serializable{

    private Long id;
    private int version;


    private Exercise1Ans exercise1Ans;
    private List<Exercise2Ans> exercise2AnsList = new ArrayList<Exercise2Ans>();
    private List<Exercise3Ans> exercise3AnsList = new ArrayList<Exercise3Ans>();
    private List<Exercise4Ans> exercise4AnsList = new ArrayList<Exercise4Ans>();
    private List<Exercise5aAns> exercise5aAnsList = new ArrayList<Exercise5aAns>();
    private List<Exercise5bAns> exercise5bAnsList = new ArrayList<Exercise5bAns>();
    private List<Exercise6Ans> exercise6AnsList = new ArrayList<Exercise6Ans>();
    private List<Exercise7Ans> exercise7AnsList = new ArrayList<Exercise7Ans>();
    private List<Exercise8Ans> exercise8AnsList = new ArrayList<Exercise8Ans>();
    private List<Exercise9aAns> exercise9aAnsList = new ArrayList<Exercise9aAns>();
    private List<Exercise9bAns> exercise9bAnsList = new ArrayList<Exercise9bAns>();
    private List<Exercise9cAns> exercise9cAnsList = new ArrayList<Exercise9cAns>();
    private List<Exercise10Ans> exercise10AnsList = new ArrayList<Exercise10Ans>();
    private List<Exercise11Ans> exercise11AnsList = new ArrayList<Exercise11Ans>();
    private List<Exercise12Ans> exercise12AnsList = new ArrayList<Exercise12Ans>();

    private static List<Class<? extends Object>> exerciseTypeList = Arrays.asList(
            Exercise1Ans.class, Exercise2Ans.class, Exercise3Ans.class,
            Exercise4Ans.class, Exercise5aAns.class, Exercise5bAns.class,
            Exercise6Ans.class, Exercise7Ans.class, Exercise8Ans.class,
            Exercise9aAns.class, Exercise9bAns.class, Exercise9bAns.class,
            Exercise10Ans.class, Exercise11Ans.class, Exercise12Ans.class);

    public ADFProgramAnswers(Exercise1Ans exercise1Ans) {
        this.exercise1Ans = exercise1Ans;
    }

    public ADFProgramAnswers() {
    }

    public List<Exercise10Ans> getExercise10AnsList() {
        return exercise10AnsList;
    }

    public void setExercise10AnsList(List<Exercise10Ans> exercise10AnsList) {
        this.exercise10AnsList = exercise10AnsList;
    }

    public void addExercise10Ans(Exercise10Ans exercise10Ans){
        this.exercise10AnsList.add(exercise10Ans);
    }

    public List<Exercise11Ans> getExercise11AnsList() {
        return exercise11AnsList;
    }

    public void setExercise11AnsList(List<Exercise11Ans> exercise11AnsList) {
        this.exercise11AnsList = exercise11AnsList;
    }

    public void addExercise11Ans(Exercise11Ans exercise11Ans){
        this.exercise11AnsList.add(exercise11Ans);
    }

    public List<Exercise12Ans> getExercise12AnsList() {
        return exercise12AnsList;
    }

    public void setExercise12AnsList(List<Exercise12Ans> exercise12AnsList) {
        this.exercise12AnsList = exercise12AnsList;
    }

    public void addExercise12Ans(Exercise12Ans exercise12Ans){
        this.exercise12AnsList.add(exercise12Ans);
    }

    public Exercise1Ans getExercise1Ans() {
        return exercise1Ans;
    }

    public void setExercise1Ans(Exercise1Ans exercise1Ans) {
        this.exercise1Ans = exercise1Ans;
    }

    public List<Exercise2Ans> getExercise2AnsList() {
        return exercise2AnsList;
    }

    public void setExercise2AnsList(List<Exercise2Ans> exercise2AnsList) {
        this.exercise2AnsList = exercise2AnsList;
    }

    public void addExercise2Ans(Exercise2Ans exercise2Ans){
        this.exercise2AnsList.add(exercise2Ans);
    }

    public List<Exercise3Ans> getExercise3AnsList() {
        return exercise3AnsList;
    }

    public void setExercise3AnsList(List<Exercise3Ans> exercise3AnsList) {
        this.exercise3AnsList = exercise3AnsList;
    }

    public void addExercise3Ans(Exercise3Ans exercise3Ans){
        this.exercise3AnsList.add(exercise3Ans);
    }

    public List<Exercise4Ans> getExercise4AnsList() {
        return exercise4AnsList;
    }

    public void setExercise4AnsList(List<Exercise4Ans> exercise4AnsList) {
        this.exercise4AnsList = exercise4AnsList;
    }

    public void addExercise4Ans(Exercise4Ans exercise4Ans){
        this.exercise4AnsList.add(exercise4Ans);
    }

    public List<Exercise5aAns> getExercise5aAnsList() {
        return exercise5aAnsList;
    }

    public void setExercise5aAnsList(List<Exercise5aAns> exercise5aAnsList) {
        this.exercise5aAnsList = exercise5aAnsList;
    }

    public void addExercise5aAns(Exercise5aAns exercise5aAns){
        this.exercise5aAnsList.add(exercise5aAns);
    }

    public List<Exercise5bAns> getExercise5bAnsList() {
        return exercise5bAnsList;
    }

    public void setExercise5bAnsList(List<Exercise5bAns> exercise5bAnsList) {
        this.exercise5bAnsList = exercise5bAnsList;
    }

    public void addExercise5bAns(Exercise5bAns exercise5bAns){
        this.exercise5bAnsList.add(exercise5bAns);
    }

    public List<Exercise6Ans> getExercise6AnsList() {
        return exercise6AnsList;
    }

    public void setExercise6AnsList(List<Exercise6Ans> exercise6AnsList) {
        this.exercise6AnsList = exercise6AnsList;
    }

    public void addExercise6Ans(Exercise6Ans exercise6Ans){
        this.exercise6AnsList.add(exercise6Ans);
    }

    public List<Exercise7Ans> getExercise7AnsList() {
        return exercise7AnsList;
    }

    public void setExercise7AnsList(List<Exercise7Ans> exercise7AnsList) {
        this.exercise7AnsList = exercise7AnsList;
    }

    public void addExercise7Ans(Exercise7Ans exercise7Ans){
        this.exercise7AnsList.add(exercise7Ans);
    }

    public List<Exercise8Ans> getExercise8AnsList() {
        return exercise8AnsList;
    }

    public void setExercise8AnsList(List<Exercise8Ans> exercise8AnsList) {
        this.exercise8AnsList = exercise8AnsList;
    }

    public void addExercise8Ans(Exercise8Ans exercise8Ans){
        this.exercise8AnsList.add(exercise8Ans);
    }

    public List<Exercise9aAns> getExercise9aAnsList() {
        return exercise9aAnsList;
    }

    public void setExercise9aAnsList(List<Exercise9aAns> exercise9aAnsList) {
        this.exercise9aAnsList = exercise9aAnsList;
    }

    public void addExercise9aAns(Exercise9aAns exercise9aAns){
        this.exercise9aAnsList.add(exercise9aAns);
    }

    public List<Exercise9bAns> getExercise9bAnsList() {
        return exercise9bAnsList;
    }

    public void setExercise9bAnsList(List<Exercise9bAns> exercise9bAnsList) {
        this.exercise9bAnsList = exercise9bAnsList;
    }

    public void addExercise9bAns(Exercise9bAns exercise9bAns){
        this.exercise9bAnsList.add(exercise9bAns);
    }

    public List<Exercise9cAns> getExercise9cAnsList() {
        return exercise9cAnsList;
    }

    public void setExercise9cAnsList(List<Exercise9cAns> exercise9cAnsList) {
        this.exercise9cAnsList = exercise9cAnsList;
    }

    public void addExercise9cAns(Exercise9cAns exercise9cAns){
        this.exercise9cAnsList.add(exercise9cAns);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static int getExerciseClassIndex(Class exerciseClass){
        return exerciseTypeList.indexOf(exerciseClass);
    }


}
