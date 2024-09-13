package org.workpal.Models;

import java.util.ArrayList;

public class Espace {
    private int id;
    private String name;
    private String location;
    private int categoryId;
    private ArrayList<Member> memberList;
    private ArrayList<DefaultService> defaultServices;


    public Espace() {
        this.defaultServices = new ArrayList<>();
        this.memberList = new ArrayList<>();
    }



    public Espace(String name, String location, int categoryId) {
        this.name = name;
        this.location = location;
        this.categoryId = categoryId;
    }

    public Espace(int id, String name, String location, int categoryId) {
        this();
        this.id = id;
        this.name = name;
        this.location = location;
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
public ArrayList<DefaultService> getDefaultServices() {
        return defaultServices;
}
public void setDefaultServices(ArrayList<DefaultService> defaultServices) {
        this.defaultServices = defaultServices;
}
public void addDefaultService(DefaultService defaultService) {
        this.defaultServices.add(defaultService);
}
public void removeDefaultService(DefaultService defaultService) {
        this.defaultServices.remove(defaultService);
}
public void addMember(Member member){
        this.memberList.add(member);
}
    public void removeMember(Member member){
        this.memberList.remove(member);
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<Member> memberList) {
        this.memberList = memberList;
    }

    @Override
    public String toString() {
        String result  =
        "Espace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category='"+ categoryId + '\''+
                ", has " + defaultServices.size() +" Services . and " + memberList.size() + "Members";
        for(int i=0; i< defaultServices.size(); i++){
            result = result + "\n Service "+(i+1)+" name :  "+defaultServices.get(i).getName();
        };

        for(int i=0; i< memberList.size(); i++){
            result = result + "\n Member "+(i+1)+" Username :  "+memberList.get(i).getUsername();
        };

        result = result + "}";
        return result;
    }
}
