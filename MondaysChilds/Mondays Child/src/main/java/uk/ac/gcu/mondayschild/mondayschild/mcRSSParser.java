package uk.ac.gcu.mondayschild.mondayschild;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by rla on 17/10/2014.
 */
public class mcRSSParser {

    private mcRSSDataItem RSSDataItem;
    boolean foundItem = false;

    public void setRSSDataItem(String sItemData)
    {
        RSSDataItem.setItemTitle(sItemData);
        RSSDataItem.setItemDesc(sItemData);
        RSSDataItem.setItemDate(sItemData);
    }

    public mcRSSDataItem getRSSDataItem()
    {
        return this.RSSDataItem;
    }

    public mcRSSParser()
    {
        this.RSSDataItem =  new mcRSSDataItem();
        setRSSDataItem(null);
    }

    public void parseRSSDataItem(XmlPullParser theParser, int theEventType)
    {
        try
        {
            while (theEventType != XmlPullParser.END_DOCUMENT)
            {
                // Found a start tag
                if(theEventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag has been found
                    if (theParser.getName().equalsIgnoreCase("item")) {
                        foundItem = true;

                    }
                    if(foundItem) {
                        Log.e("found","yup");
                        if (theParser.getName().equalsIgnoreCase("title")) {
                            Log.e("found","yup");
                            // Now just get the associated text
                            String temp = theParser.nextText();
                            Log.e("found",temp);
                            // store data in class
                            if (RSSDataItem.getItemTitle() != null) {
                                RSSDataItem.setItemTitle(RSSDataItem.getItemTitle() + "~split~" + temp);
                                Log.e("got1","yep");
                            }
                            else RSSDataItem.setItemTitle(temp);
                        }
                        else
                            // Check which Tag we have
                            if (theParser.getName().equalsIgnoreCase("description")) {
                                // Now just get the associated text
                                String temp = theParser.nextText();
                                temp = temp.replace("<span class=\"meta-nav\">&#8594;</span>", "");
                                temp = temp.replace("<", "&lt;");
                                temp = temp.replace("&#8217;", "'");
                                // store data in class
                                if (RSSDataItem.getItemDesc() != null) {
                                    RSSDataItem.setItemDesc(RSSDataItem.getItemDesc() + "~split~" + temp);
                                }
                                else RSSDataItem.setItemDesc(temp);
                            }
                            else
                                // Check which Tag we have
                                if (theParser.getName().equalsIgnoreCase("pubDate")) {
                                    // Now just get the associated text
                                    String temp = theParser.nextText();
                                    // store data in class
                                    if (RSSDataItem.getItemDate() != null) {
                                        RSSDataItem.setItemDate(RSSDataItem.getItemDate() + "~split~" + temp);
                                    }
                                    else RSSDataItem.setItemDate(temp);
                                }
                    }
                }
                // Get the next event
                theEventType = theParser.next();

            } // End of while

        }
        catch (XmlPullParserException parserExp1)
        {
            Log.e("MyTag","Parsing error" + parserExp1.toString());
        }

        catch (IOException parserExp1)
        {
            Log.e("MyTag","IO error during parsing");
        }

    }

    public void parseRSSData(String RSSItemsToParse) throws MalformedURLException {
        URL rssURL = new URL(RSSItemsToParse);
        InputStream rssInputStream;
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();
            parseRSSfactory.setNamespaceAware(true);
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");
            RSSxmlPP.setInput(new StringReader(xmlRSS));
            int eventType = RSSxmlPP.getEventType();

            parseRSSDataItem(RSSxmlPP,eventType);

        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");
    }

    public InputStream getInputStream(URL url) throws IOException
    {
        return url.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, charsetName);
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }
}
