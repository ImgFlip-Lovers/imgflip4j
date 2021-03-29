[![](https://jitpack.io/v/adriens/imgflip4j.svg)](https://jitpack.io/#adriens/imgflip4j)

# imgflip4j

Java SDK to interact with imgflip

Because interacting with [Imgflip](https://imgflip.com/) from Java should be as easy as :

```java
System.out.println("-- Top 10 most captionned memes --");
int i = 1;
for (Meme meme : getTopNMemes(10)) {
    System.out.println(i + ". " + meme);
    i++;
}
```


# Resources

- [Imgflip API](https://imgflip.com/api)
