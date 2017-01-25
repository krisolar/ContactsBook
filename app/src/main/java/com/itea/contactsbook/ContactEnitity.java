package com.itea.contactsbook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by A on 19.01.2017.
 */

public class ContactEnitity implements Parcelable{

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getPhotoContact() {
        return photoContact;
    }

    public void setPhotoContact(Uri photoContact) {
        this.photoContact = photoContact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPnoneNumber1() {
        return pnoneNumber1;
    }

    public void setPnoneNumber1(String pnoneNumber1) {
        this.pnoneNumber1 = pnoneNumber1;
    }

    public String getPnoneNumber2() {
        return pnoneNumber2;
    }

    public void setPnoneNumber2(String pnoneNumber2) {
        this.pnoneNumber2 = pnoneNumber2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String id;
    private Uri photoContact;
    private String name;
    private String familyName;
    private String pnoneNumber1;
    private String pnoneNumber2;
    private String email;

    public ContactEnitity() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeParcelable(photoContact, i);
        parcel.writeString(name);
        parcel.writeString(familyName);
        parcel.writeString(pnoneNumber1);
        parcel.writeString(pnoneNumber2);
        parcel.writeString(email);
    }

    protected ContactEnitity(Parcel in) {
        id = in.readString();
        photoContact = in.readParcelable(Uri.class.getClassLoader());
        name = in.readString();
        familyName = in.readString();
        pnoneNumber1 = in.readString();
        pnoneNumber2 = in.readString();
        email = in.readString();
    }

    public static final Creator<ContactEnitity> CREATOR = new Creator<ContactEnitity>() {
        @Override
        public ContactEnitity createFromParcel(Parcel in) {
            return new ContactEnitity(in);
        }

        @Override
        public ContactEnitity[] newArray(int size) {
            return new ContactEnitity[size];
        }
    };

}
