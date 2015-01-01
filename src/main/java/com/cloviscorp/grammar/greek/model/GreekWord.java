package com.cloviscorp.grammar.greek.model;

import com.cloviscorp.grammar.Letters;
import com.cloviscorp.grammar.SyllableException;
import com.cloviscorp.grammar.Word;
import org.thymeleaf.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.LinkedList;

/**
 * Created by markcbordelon on 12/28/14.
 */


@Entity
@Table(name="words", catalog="greek")
public class GreekWord extends Word {

    // for lexeme and morphmeme use entity relationship and let the container take care of it

    public String getWord() { return str.getTranslit(); }
    public void setWord(String translit) {
        GreekString str = new GreekString();
        str.setTranslit(translit); if (str.getTranslit().equals(str.getPresent())) { str.setPresent(translit); }
        this.str = str;

        /* this.syllables = hyphenate(this.str); */
    }


    //@Transient
    //public GreekLexeme getLexeme() { return (GreekLexeme) lexeme; }

    //@Transient
    //public GreekMorpheme getMorpheme() { return (GreekMorpheme) morpheme; }

    //@Transient
    //public GreekString getString() { return (GreekString) str; }

    public LinkedList<GreekSyllable> hyphenate(GreekString str) { // in GreekWord
        LinkedList<GreekSyllable> syls = new LinkedList<GreekSyllable>();
        syls.add(new GreekSyllable(str));
        syllabify(syls);
        // syls.removeFirst();
        return syls;
    }

    private void syllabify(LinkedList<GreekSyllable> syls) {
        try {
            Letters finalstr = syls.getLast().getFinals();
            // break the final into 0.final and 1.initial (try to use logic already in new GreekSyllable)
            Letters final0 = finalstr.substring(0,2);
            Letters initial1 = finalstr.substring(3);
            syls.getLast().setFinals(final0);
            GreekString str = new GreekString(initial1);
            GreekSyllable syl = new GreekSyllable(str);
            syls.add(syl);
            return;
        } catch (SyllableException e) {
            syllabify(syls);
        }
    }


    /*
    public void syllabify (LinkedList<Syllable> syls) {
        if (no_of_vowels(phonemes)==1) {
            mark_syllable_boundary(at_the_end_of_phonemes);
        else
            if (is_a_vowel(phonemes[current_position])) {
                no_of_consonants = count_no_of_consonants_upto_next_vowel(phonemes,current_position);
                if (no_of_consonants == 0) {
                 if (is_a_vowel(phonemes[current_position + 1])) {
                     mark_syllable_boundary(current_position) // Rule#3
                     syllabify(phonemes, current_position + 1);
                 }
            } else if (no_of_consonants == 1) {
                    mark_syllable_boundary(current_position) // Rule#1
                    syllabify(phonemes, current_position + 2);
                }
            if (no_of_consonants == 2) {
                mark_syllable_boundary(current_position + 1);
                syllabify(phonemes, current_position + 3) //Rule#2
            }
            if (no_of_consonants == 3) {
                if (phonemes[current_position+3] in ("r","y") {
                    mark_syllable_boundary(current_position+1); //Rule#4
                    syllabify(phonemes, current_position+4);
            else
                if (is_a_stop(phonemes[current_posi+1]) && is_a_stop(phonemes[current_posi+2])){
                        mark_syllable_boundary(current_position + 1); //  Rule#5
                        syllabify(phonemes, current_position + 4)
                else
                        mark_syllable_boundary(current_position + 2); //  Rule#6
                        syllabify(phonemes, current_position + 4)
                    }
                }
            }
            if no_of_consonants are greater than 3 then
                if (phonemes[current_position+no_of_consonants] in ( “r” or “y”)){
                    mark_syllable_boundary(current_position + no_of_consonants - 2) // Rule#7
                    Syllabify(phonemes, current_position + no_of_consonants - 1)
                    else
                    syllable_boundary = find_min_sonority_position(phonemes, current_postion);
                    mark_syllable_boundary(syllable_boundary); //  Rule#8
                    Syllabify(phonemes, syllable_boundary + 1);
                }
            }
    }
    else
        temp = current_postion
            repeat
                temp = temp + 1;
            until((is_a_vowel(phonemes[temp]))
            syllabify(phonemes,temp);
}
}
}
*/


    /* greek word would have a no_diacritics method */


// recursion with tree or linked-list
/*
    katalamba/nesthe
stem and ending lookup:  kata  lamb-a/n e-sthe (only costly if lex or morph be unknown)
  ka ta lamb a/n e sthe
without stem and ending lookup:
  ka ta lam ba/ nes the

// v-v   v-cv    vc-cv
// vccv vcccv vccccv vcccccv

// STEM RULES
// first seek any stem in word and divide using that (linked list)

// CONSONANT RULES
// (already queried dictionary for consonantal syllable starts: consonant(s) leading up to vowel = Word.WORDSTARTS)
// any single Letter.CONSONANT between two Letter.VOWELS begins a syllable (go thru list, breaking up syls)
// any double Letter.CONSONANT between two Letter.VOWELS splits the double consonant (ditto)
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
        // DEBUG("after startcon analysis  :"+str+" = "+str);

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
        // CHANGE THIS TO createSimilarSyllables(syl, num)
        // first get syllable type: V VC VCC, CV, CVC, CVCC, CCV, CCVC, CCVCC
        // then create num of the same type, replace one letter each time with a different letter of the same type

        // create a syllable list, adding to global Syllable Array
        // put all stems and endings (and words from definition) into a word array
        // divide all these into syllables, adding them to a syllable arr
        // the list will have unique syllables and nont will be in d

        // load the new syllables into LanguagePhonetics.Syls
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

    */



    @Override
    public String toString() {
        return "Word{#" + /*id +*/
                //", translit='" + getTranslit() + '\'' +
                //", present='" + getPresent() + '\'' +
                ", syllables='" + StringUtils.join(syllables, '-')+ '\'' +
                ", lexeme='" + lexeme + '\'' +
                ", morpheme='" + morpheme + '\'' +
                '}';
    }

}

