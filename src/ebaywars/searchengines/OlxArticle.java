package ebaywars.searchengines;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OlxArticle {
	
	private String articleTitle;
	private String articlePrice;
	private String articleLink;
	private String articleId;
	private String articleLocation;
	private String articleDate;
	
	public OlxArticle (String link)
	{
		articleLink = link;
		
		try
		{
			Document article = Jsoup.connect(articleLink).userAgent("Mozilla/17.0").get();
			articleTitle = article.select("span#naslovartikla").first().text();
			articlePrice = article.select("div#pc").select("p:not(.n)").text().replaceFirst(" KM", "");
			articleId = articleLink.substring(27, 35);
			articleDate = article.select("time.entry-date").first().text();
			articleLocation = article.select("div.op.pop.mobile-lokacija").select("a").text().replaceFirst("Lokacija ", "");
					
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void printBasicData()
	{
		System.out.println("Link: " + this.getArticleLink());
		System.out.println("Naslov: " + this.getArticleTitle());
		System.out.println("Cijena: " + this.getArticlePrice());
		System.out.println("Id: " + this.getArticleId());
		System.out.println("Datum: " + this.getArticleDate());
		System.out.println("Lokacija: " + this.getArticleLocation());
	}
	
	public String getArticleTitle()
	{
		return articleTitle;
	}
	
	public String getArticlePrice()
	{
		return articlePrice;
	}
	
	public String getArticleLink()
	{
		return articleLink;
	}
	
	public String getArticleId()
	{
		return articleId;
	}
	
	public String getArticleDate()
	{
		return articleDate;
	}
	
	public String getArticleLocation()
	{
		return articleLocation;
	}
	

}
