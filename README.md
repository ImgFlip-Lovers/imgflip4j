[![](https://jitpack.io/v/adriens/imgflip4j.svg)](https://jitpack.io/#adriens/imgflip4j)

# imgflip4j

Java SDK to interact with imgflip

Because interacting with [Imgflip](https://imgflip.com/) from Java should be as easy as :

## Top 10 most captionned memes

```java
System.out.println("-- Top 10 most captionned memes --");
int i = 1;
for (Meme meme : ImgFlipAPI.getTopNMemes(10)) {
    System.out.println(i + ". " + meme);
    i++;
}
```

## Published memes

```java
List<PublishedMeme> memes = PublishedMemesCrawler.getPublishedMemes();
int i = 1;
for (PublishedMeme aMeme : memes) {
  System.out.println(i + ". " + aMeme);
  i++;
}
```

## Popular streams

```java
logger.info("get populars streams (NSFW not included)");
List<PopularStream> defaultPopularStreams = PopularStreamCrawler.getPopularStreams();
for (PopularStream popularStream : defaultPopularStreams) {
    logger.info("title : {} {}", popularStream.getTitle(), popularStream.getNSFW() ? "(NSFW)" : "");
}
logger.info("Count : {}", defaultPopularStreams.size());

logger.info("get populars NSFW streams (only)");
List<PopularStream> nsfwPopularStreams = PopularStreamCrawler.getPopularNSFWStreams();
for (PopularStream popularStream : nsfwPopularStreams) {
    logger.info("title : {} {}", popularStream.getTitle(), popularStream.getNSFW() ? "(NSFW)" : "");
}
logger.info("Count : {}", nsfwPopularStreams.size());
```

## Top Users

```java
logger.info("get top users");
List<TopUser> users = TopUserCrawler.getTopUsers();
for (TopUser user : users) {
    logger.info("user : {} {}", user.getRank(), user.getUser());
}
logger.info("Count : {}", users.size());
```

## User

```java
final String userName = "userName";

String url = getUserUrl(userName);
User user = getUser(userName);

logger.info("user {} ({}) : {}", userName, url, user);
```

## Resources

- [Imgflip API](https://imgflip.com/api)
