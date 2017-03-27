package com.example.storm.moviesinfo.model.movielist;

/**
 * Created by khb on 2017/3/27.
 */

class MovieBrief {
    String tvTitle;
    String iconaddress;
    String iconlinkUrl;
    String m_iconlinkUrl;
    String subHead;
    String grade;
    String gradeNum;
    PlayDate playDate;

    public class PlayDate {
        String showname;
        String data;
        String data2;

        public String getShowname() {
            return showname;
        }

        public void setShowname(String showname) {
            this.showname = showname;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getData2() {
            return data2;
        }

        public void setData2(String data2) {
            this.data2 = data2;
        }
    }

    public class Diractor{
        String showname;

    }

    public class People{
        public class People1{
            String name;
            String link;
        }
        public class Peoplem1{
            String link;
        }

    }

}
