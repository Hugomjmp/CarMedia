package pt.hugo.carmedia.models.data.radio;

public class Station {
    private String name;
    private String codec;
    private String url;
    private String homepage;
    private String favicon;
    private String countrycode;
    private String country;
    private String countrysubdivisioncode;
    private String countrysubdivision;
    private String languagecodes;
    private String languages;
    private String geoinfo;


    public Station(String name,
                   String codec,
                   String url,
                   String homepage,
                   String favicon,
                   String countrycode,
                   String country,
                   String countrysubdivisioncode,
                   String countrysubdivision,
                   String languagecodes,
                   String languages,
                   String geoinfo) {

        this.name = name;
        this.codec = codec;
        this.url = url;
        this.homepage = homepage;
        this.favicon = favicon;
        this.countrycode = countrycode;
        this.country = country;
        this.countrysubdivisioncode = countrysubdivisioncode;
        this.countrysubdivision = countrysubdivision;
        this.languagecodes = languagecodes;
        this.languages = languages;
        this.geoinfo = geoinfo;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getFavicon() {
        return favicon;
    }

    @Override
    public String toString() {
        return "\nStation{" +
                "name='" + name + '\'' +
                ", codec='" + codec + '\'' +
                ", url='" + url + '\'' +
                ", homepage='" + homepage + '\'' +
                ", favicon='" + favicon + '\'' +
                ", countrycode='" + countrycode + '\'' +
                ", country='" + country + '\'' +
                ", countrysubdivisioncode='" + countrysubdivisioncode + '\'' +
                ", countrysubdivision='" + countrysubdivision + '\'' +
                ", languagecodes='" + languagecodes + '\'' +
                ", languages='" + languages + '\'' +
                ", geoinfo='" + geoinfo + '\'' +
                '}';
    }
}
