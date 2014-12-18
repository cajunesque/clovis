package com.programmingfree.springservice;

import org.thymeleaf.util.StringUtils;

import java.util.List;

public abstract class Word { // derive LatinWord, GreekWord, etc each with persistence to cat=<lang>. tab=<WORD>

    //private Context id; // combination of textid and wordid, used to getPhrase and getClause

    private String translit;
    private String present;
    private List<Syllable> syllables;
    private Lexeme lexeme; // if null then ambiguous
    private Morpheme morpheme; // if null then ambiguous
    // private Syntax syntax; // syntax in its clause

    /*
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    */
    public String getTranslit() {
        return translit;
    }
    public void setTranslit(String translit) {
        this.translit = translit;
    }

    public String getPresent() {
        return present;
    }
    public void setPresent(String present) {
        this.present = present;
    }

    public void hyphenate() {
        // run a generic algorithm on Letters
        // make use of letter types to create Syllable and load Syllable list

    }


// STEM RULES
// first seek any stem in word and divide using that (linked list)
// CONSONANT RULES
// (already queried dictionary for consonantal syllable starts: consonant(s) leading up to vowel = Word.WORDSTARTS)
// any single Letter.CONSONANT between two Letter.VOWELS begins a syllable (go thru list, breaking up syls)
// groups of Letter.CONSONANTs are split before Word.WORDSTARTS (wordstarts=sylstarts) (ditto)
// VOWEL RULE
// groups of Letter.VOWELs are split before Letter.DIPHTHONG (go thru list, breaking up syls)
// diacritics are ignored
// stop when each element in linkedlist contains one vowel or diphthong


        if (LanguagePhonetics.LangName=="LATIN") {
            LanguagePhonetics.Cons = new String("b bl br c ch chr cl cr d dr f fl fr g gl gn gr gu h j k l m n p ph pl pr qu r s sc sch su scr sp st str t th thr tr v");
            LanguagePhonetics.Vowels = new String("a e i o u y a^ e^ i^ o^ u^ y^ ae au ei eu oi ui");
        } else if (LanguagePhonetics.LangName=="GREEK") {
            LanguagePhonetics.Cons = new String("` b bl br d dr g gl gn gr h k kh khl khr kl kn kr ks kt l m mn n p ph phl phr phth pl pn pr ps pt r rh s sk skh skl sm sp sph spl st sth str t th thn thr tm tr ts");
            LanguagePhonetics.Vowels = new String("a e i o u a^ e^ i^ o^ u^ ai au ei eu oi ou ui a^i e^i o^i");
            LanguagePhonetics.Diacr = new String("/ \\");
        } else if (LanguagePhonetics.LangName=="SANSKRIT") {
            LanguagePhonetics.Cons = new String("b bh c c^ ch d d^ d^h dh g gh h hh j jc^ jh k k^ kh ks^ l m n n^ p ph r s s^ sh t t^ t^h th v y tr");
            LanguagePhonetics.Vowels = new String("a aa i ii u uu r^ rr^ e ai o au a~ hh l^ ll^");
            LanguagePhonetics.Diacr = new String("");
        } else if (LanguagePhonetics.LangName=="ASSYRIAN") {
            LanguagePhonetics.Cons = new String("` b bh g gh d dh h w z h^ t^ y k kh l m n s `^ p ph c q r s^ t th");
            LanguagePhonetics.Vowels = new String("a e i o u a^ e^ i^ o^ u^ ai au ei eu oi ou ui");
            LanguagePhonetics.Diacr = new String("/");
        } else if (LanguagePhonetics.LangName=="FRENCH") {
            LanguagePhonetics.Cons = new String("b bl br c cl cr ch d dr f fl fr g gl gr gn h j k l ll m n p pl pr ph qu r s sl t tr th v vr w y z");
            LanguagePhonetics.Vowels = new String("a ai au e eau eu ei i o oe oi oeu ou u");
            LanguagePhonetics.Diacr = new String("/ \\");
        } else if (LanguagePhonetics.LangName=="JAPANESE") {
            LanguagePhonetics.Cons = new String("b by ch d dy f g gy h hy j k ky m my n ny p py r ry s sh t ty ts w y z zy");
            LanguagePhonetics.Vowels = new String("a e i o u");
            LanguagePhonetics.Diacr = new String("");
        } else { // ENGLISH
            LanguagePhonetics.Cons = new String("b bl br c cl cr ch d dr f fl fr g gl gr h j k l m n p pl pr ph qu r s sl t tr th v w y z");
            LanguagePhonetics.Vowels = new String("a ai au e eau eu i o oe oi oeu ou u");
            LanguagePhonetics.Diacr = new String("/");
        }
        // sort by length
        var temp;
        temp = LanguagePhonetics.Cons.split(' '); temp.sort(byLength); LanguagePhonetics.Cons = ' '+temp.join(' ')+' ';
        temp = LanguagePhonetics.Vowels.split(' '); temp.sort(byLength); LanguagePhonetics.Vowels = ' '+temp.join(' ')+' ';
        temp = LanguagePhonetics.Diacr.split(' '); temp.sort(byLength); LanguagePhonetics.Diacr = ' '+temp.join(' ')+' ';


    function analyze(strng) { // return a string with all vowels, and consonant types marked, exactly as long as the original string
        var str=strng.toLowerCase();
        var pos;

        // mark consonants greedily 1(+)*
        if (str.length>1) {
            for (var i = 0; i < str.length; i++) {
                for (var j = str.length; j>i; j--) {
                    pos = LanguagePhonetics.Cons.indexOf(' '+str.substr(i,j-i)+' ');
                    if (pos>=0) {
                        //DEBUG("BEFORE GROUP="+str);
                        str = str.substr(0,i)+  "1" +  "+++++".substring(0,j-i-1)   +str.substr(j);
                        //DEBUG("AFTER GROUP="+str);
                    }
                }
            }
        }
        // DEBUG("after startcon analysis  :"+strng+" = "+str);

        // mark all vowels greedily as 0(+)*, di- and tri-phthongs are also grouped here
        if (str.length>1) {
            for (var i = 0; i < str.length; i++) {
                for (var j = str.length; j>i; j--) {
                    pos = LanguagePhonetics.Vowels.indexOf(' '+str.substr(i,j-i)+' ');
                    if (pos>=0) {
                        //DEBUG("BEFORE VOWEL ="+str);
                        str = str.substr(0,i)+  "0" +  "+++++".substring(0,j-i-1)   +str.substr(j);
                        //DEBUG("AFTER VOWEL="+str);
                    }
                }
            }
        }

        // mark diacriticals
        if (str.length>1) {
            for (var i = 0; i < str.length; i++) {
                pos = LanguagePhonetics.Diacr.indexOf(' '+str.charAt(i)+' ');
                if (pos>=0) {
                    //DEBUG("BEFORE VOWEL ="+str);
                    str = str.substr(0,i)+  "+"  +str.substr(i+1);
                    //DEBUG("AFTER VOWEL="+str);
                }
            }
        }
        // DEBUG("ANALYSIS AFTER DIACR MARKING "+strng+" = "+str);

        // DEBUG("after start-con/vowel/diacritic analysis "+strng+" = "+str);

        return str;
    }

    function dividebysyls(s) {
        if (!s || s.length==0) return "";
        var phon = analyze(s)+'1';
        var ret = "";
        var ch, ch0, ch1, ch2;
        for (var i = 0; i < phon.length-2; i++) {
            ch0 = phon.charAt(i); ch1 = phon.charAt(i+1);
            if (ch0=='-') { ret += '-'; continue;}

            if (ch0!='+')	ch = ch0; // running type of plus
            else		ch0 = ch;

            // patterns and hyphenation are
            // ch0 ch1 ch2 ex		hyph between ch0 and ch1
            //   0    0	  aa		1	(always separate two vowels)
            //   1    0    	  ba		0	(never separate consonant from following vowel)
            //   0    1    0/1 aba/abb	1/0
            //   1    1    0/1 bba/bbb	1/0
            if 	(ch0=='1' && ch1=='0') ret += s.charAt(i);
            else if (ch0=='0' && ch1=='0') ret += s.charAt(i)+'-';
            else if (ch1=='1') {
                var j = i+2; while (phon.charAt(j)=='+') j++; // get "ch2"
                if (phon.charAt(j)=='0' ) ret += s.charAt(i)+'-';
                else ret += s.charAt(i);
            } else ret += s.charAt(i);
        }
        ret += s.charAt(i);

        while (ret.indexOf("--")>=0) ret = ret.replace("--","-");
        ret = ret.replace(/^\-|\-$/g, '');

        if (ret==s && ret.indexOf('-')<1) { // the result of the syllable division is the string itself and there one syllable only
            // NO OP there is no need to divide by individual letters here
            // DEBUG("DIVIDE BY SYLS could not divide '"+s+"' into syllables (is itself one syllable), might divide in letters here if instructed to");
        }
        // DEBUG("DIVIDE BY SYLS: done. "+s+" -> "+ret)
        return ret;
    }


    function dividebyparts(s, stemlist, endlist) {
        var olds = s;

        // order stems and ends lists by length of part
        var arr;

        // hyphenate on ends using mixed case
        if (endlist) {
            arr = endlist.split(','); arr.sort(byLength); endlist = ','+arr.join(',')+',';
            for (var i = 1; i < s.length; i++) {
                var end = s.substr(i);
                var replpos = endlist.indexOf(','+end+',');
                if (replpos>=0) {
                    s = s.substr(0,i) + s.charAt(i).toUpperCase() + s.substr(i+1);
                    endlist = endlist.replace(','+end+',',',');
                }
            }
            DEBUG("DIVIDE BY PARTS: after hyphenating on ends (i.e. "+endlist+") = "+s);
        }

        // hypenate on stems using mixed case
        if (stemlist) {
            arr = stemlist.split(','); arr.sort(byLength); stemlist = ','+arr.join(',')+',';
            DEBUG("DIVIDE BY PARTS: before hyphenating on stems (i.e. "+stemlist+")  = "+s);
            for (var i = 0; i < s.length; i++) {
                for (var j = s.length; j>i; j--) {
                    var part = s.substr(i,j-i); // TODO remove accents in Greek but remember them adjustment to j pos
                    if (DrillSession.Language.toUpperCase()=="GREEK") part = part.replace('/','').replace('\\','');
                    var replpos = stemlist.indexOf(','+part+',');
                    if (replpos>=0) {
                        //debug.value += ("debug.value += found "+' '+part+' '+" in "+stemlist); DEBUG("BEFORE CORRECT="+s);
                        s = s.substr(0,i) + s.charAt(i).toUpperCase() + s.substr(i+1,j-i-1) + s.charAt(j).toUpperCase() + s.substr(j+1);
                        stemlist = stemlist.replace(','+part+',',','); // remove this part from the stemlist
                        //DEBUG("AFTER CORRECT="+s); DEBUG("stemlist now = "+stemlist);
                    }
                }
            }
            DEBUG("DIVIDE BY PARTS: after hyphenating on stems (i.e. "+stemlist+")  = "+s);
        }


        var ret = ""; // transform mixed case into hyphens
        for (var i = 0; i < s.length; i++)
            ret += (s.charAt(i)>='A' && s.charAt(i)<='Z' && olds.charAt(i)>='a' && olds.charAt(i)<='z')?
                    ("-"+s.charAt(i).toLowerCase()) :
                    s.charAt(i);
        if (ret.charAt(0)=='-') ret = ret.substr(1);
        return ret;
    }

    function hyphenate(d,s,e) { // hypenate intelligently, using stems and endings first
        if (s!=null || e!=null) {
            // divide stems and endings into their own syllables (often results in individual letters -- remove these?)
            // if (s!=null) { var temp = s.split(','); for (var i = 0; i < temp.length; i++) temp[i]=dividebysyls(temp[i]).replace(/-/g,','); s = temp.join(','); }
            // if (e!=null) { var temp = e.split(','); for (var i = 0; i < temp.length; i++) temp[i]=dividebysyls(temp[i]).replace(/-/g,','); e = temp.join(','); }
            d = dividebyparts(d,s,e);
        }
        d = dividebysyls(d);
        DEBUG("HYPHENATE: after dividing by syllables = "+d);
        // if there are no hyphens in d, hyphenate every letter

        return d;
    }




    function addDistractorSyllables(d, s, e, text) { // add syllables using s, e, text to  d
        // create a syllable list, adding to global Syllable Array
        // put all stems and endings (and words from definition) into a word array
        // divide all these into syllables, adding them to a syllable arr
        // the list will have unique syllables and nont will be in d

        // load the new syllables into LanguagePhonetics.Syls
        if (!s) s = '';	if (!e) e = '';	if (!text) text = '';
        var wordarr = (s.split(',').join(' ')+' '+e.split(',').join(' ')+' '+text.replace(/\<\>/g,'')).replace(/  /g,' ').split(' ');
        for (var i = 0; i < wordarr.length; i++) {
            var word = wordarr[i]; word = word.replace(/^[\[\(\"\']*/g,'').replace(/[\]\)\"\'\.,;:!?]*$/g,'');
            var syls = dividebysyls(word); if (syls == "") continue;
            var wordsylarr = dividebysyls(word).split('-');
            for (var j = 0; j < wordsylarr.length; j++)
                if (LanguagePhonetics.Syls.indexOf(wordsylarr[j]) < 0)
                    LanguagePhonetics.Syls.push(wordsylarr[j]);
        }

        d = trim(d);
        var originalarr = d.split('-'); // syllables of d which we are distracting for

        // load a 100 element sarr with all words from SyllbleArray excluding the original syls
        var sarr = new Array();
        for (var i = 0; i < LanguagePhonetics.Syls.length; i++)
            if (originalarr.indexOf(LanguagePhonetics.Syls[i]) < 0)
                sarr.push(LanguagePhonetics.Syls[i]);
        sarr.sort(byRandom);
        sarr = sarr.slice(0, 100);
        //DEBUG("ADD DISTRACTOR SYLLABLES: pool from which to find distractor syls: "+sarr);

        // now sort the small sarr by closeness to syllables in d into an array called sarrtotal
        var closesyls = new Object(); // sarrpart = new Array(), sarrtotal = new Array();
        for (var i = 0; i < originalarr.length; i++) {
            var origsyl = originalarr[i];
            closesyls[origsyl] = sarr.slice(0); // starts with random syls
            closesyls[origsyl].sort( function(astr,bstr) {
                return EditDistance(astr,origsyl) - EditDistance(bstr,origsyl);
            } ); // sort function
        }
        // from closesyls[ num-orig-syls ], recreate sarr
        sarr = new Array();
        for (var i = 0; i < originalarr.length; i++) {
            for (var j = 0; j < Math.min(2,closesyls[originalarr[i]].length); j++) {
                var syl = closesyls[originalarr[i]][j];
                if (sarr.indexOf(syl) < 0) sarr.push(syl);
            }
        }

        DEBUG("ADD DISTRACTOR SYLLABLES: syllables similar to "+d+": "+sarr);

        sarr.sort(byRandom);
        // now skim off just the right amount:
        var rightamount = originalarr.length/2;
        if (rightamount<3) rightamount = 3; if (rightamount>sarr.length) rightamount = sarr.length;
        sarr = sarr.slice(0, rightamount );
        DEBUG("ADD DISTRACTOR SYLLABLES: right amount of distractor syllables, ["+3+", "+originalarr.length/2+", "+sarr.length+"] = "+rightamount+", gives "+sarr);

        return originalarr.concat(sarr); // the list of original syllables and distractor syllables
    }

    function addDistractorLetters(d) { // split on presentation letters instead and then get translit for these
        DEBUG("starting word (makes call to inflectedwordsview) = "+d);
        var presword = PresentText(d);
        var presdistractorword = GetData("SELECT top 1 "+DrillSession.Language+".topresentation(word) As preslets FROM "+DrillSession.Language+".inflectedwordsView WHERE word<>'"+d+"' And len(word) = "+d.length+" ORDER BY master.editdistance(word, '"+d+"')")[0].preslets;
        DEBUG("presword="+presword+" presdistractorword="+presdistractorword);

        var letterarr = presword.replace( new RegExp("(.)", "g") ,"-$1").substr(1).split('-');
        // add to letter arr all the letter of presdistractorword that are not in already there (presword)
        for (var i = 0; i < presdistractorword.length; i++) {
            var distractorletter = presdistractorword.charAt(i);
            if (letterarr.indexOf(distractorletter)<0) {
                DEBUG("Adding distractor letter ("+distractorletter+") to letterarr: ("+letterarr+")");
                letterarr.push( distractorletter );
            }
        }

        DEBUG("letterarr="+letterarr)
        // convert all the letters in distracotarr to translit
        DEBUG("sql = "+"SELECT "+DrillSession.Language+".totransliteration( N'"+letterarr.join(' ')+"' ) As translets");
        var translets = GetData("SELECT "+DrillSession.Language+".totransliteration( N'"+letterarr.join(' ')+"' ) as translets")[0].translets;
        DEBUG("transit letters = "+translets);

        return translets.split(' ');
    }





    @Override
    public String toString() {
        return "Word{#" + /*id +*/
                ", translit='" + translit + '\'' +
                ", present='" + present + '\'' +
                ", syllables='" + StringUtils.join(syllables, '-')+ '\'' +
                ", lexeme='" + lexeme + '\'' +
                ", morpheme='" + morpheme + '\'' +
                '}';
    }

}

