package kh.edu.rupp.fe.dse.myapplication.model;


import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ProfileData data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ProfileData getData() {
        return data;
    }

    public static class ProfileData {
        @SerializedName("firstName")
        private String firstName;

        @SerializedName("lastName")
        private String lastName;

        @SerializedName("bio")
        private String bio;

        @SerializedName("profileImage")
        private String profileImage;

        @SerializedName("coverImage")
        private String coverImage;

        @SerializedName("friendCount")
        private int friendCount;

        @SerializedName("job")
        private String job;

        @SerializedName("workPlace")
        private String workPlace;

        @SerializedName("maritalStatus")
        private String maritalStatus;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getBio() {
            return bio;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public int getFriendCount() {
            return friendCount;
        }

        public String getJob() {
            return job;
        }

        public String getWorkPlace() {
            return workPlace;
        }
    }
}
