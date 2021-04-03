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

# Resources

- [Imgflip API](https://imgflip.com/api)
