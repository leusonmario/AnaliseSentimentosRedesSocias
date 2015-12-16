# coding=utf-8

import tweepy
import json
import xlwt
import datetime
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import time
 
# Consumer keys and access tokens, used for OAuth
consumer_key = ''
consumer_secret = ''
access_token = ''
access_token_secret = ''

# OAuth process, using the keys and tokens
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

# planilha xls
workbook = xlwt.Workbook();
worksheet = workbook.add_sheet(u'Tweets')

api = tweepy.API(auth)
#q = 'x-men apocalipse #xmen OR #x-men OR #xmenapocalipse OR #x-menapocalipse lang:pt'
#'starwars odespertardaforca odespertardaforça starwarsbr #starwars OR #odespertardaforca OR #odespertardaforça OR #starwarsbr lang:pt'
q = '#NaoVaiTerGolpe OR #VemPraRua' 
#INICIO 20:44 UFC
#INICIO 11:47 golpe
count = 2000
search_results = api.search(q=q, count=count)

iteradorTweets = 0

now = datetime.datetime.now()
finalParada = now.replace(day=13, hour=19, minute=0, second=0, microsecond=0)

worksheet.write(iteradorTweets,0,'TweetCriacao')
worksheet.write(iteradorTweets,1,'TweetID')
worksheet.write(iteradorTweets,2,'NomeUsuario')
worksheet.write(iteradorTweets,3,'IDUsuario')
worksheet.write(iteradorTweets,4,'DataCriacaoConta')
worksheet.write(iteradorTweets,5,'NumFavoritosUsuario')
worksheet.write(iteradorTweets,6,'NumTweetsUsuario')
worksheet.write(iteradorTweets,7,'NumAmigosUsuario')
worksheet.write(iteradorTweets,8,'LinguaUsuario')
worksheet.write(iteradorTweets,9,'TwitterProtegido')
worksheet.write(iteradorTweets,10,'NumSeguidoresTwitter')
worksheet.write(iteradorTweets,12,'TweetTexto')
worksheet.write(iteradorTweets,13,'TweetResposta')
worksheet.write(iteradorTweets,14,'RetweetIDNome')
worksheet.write(iteradorTweets,15,'RetweetID')
iteradorTweets = 1
idUltimo = 0

while (now < finalParada):
	search_results = api.search(q=q, count=count, max_id = idUltimo)
	for data in search_results:
		#if iteradorTweets%10 == 0:
			#idUltimo = data.id
		if not data.retweeted and 'RT @' not in data.text:
			print(iteradorTweets)
			idUltimo = data.id
			worksheet.write(iteradorTweets,0,data.created_at)
			worksheet.write(iteradorTweets,1,data.id)
			worksheet.write(iteradorTweets,2,data.user.screen_name)
			worksheet.write(iteradorTweets,3,data.user.id)
			worksheet.write(iteradorTweets,4,data.user.created_at)

			worksheet.write(iteradorTweets,5,data.user.favourites_count)
			worksheet.write(iteradorTweets,6,data.user.statuses_count)
			worksheet.write(iteradorTweets,7,data.user.friends_count)
			worksheet.write(iteradorTweets,8,data.user.lang)
			worksheet.write(iteradorTweets,9,data.user.protected)
			worksheet.write(iteradorTweets,10,data.user.followers_count)

			worksheet.write(iteradorTweets,12,data.text.lower())
			worksheet.write(iteradorTweets,13,data.in_reply_to_user_id)
			worksheet.write(iteradorTweets,14,data.in_reply_to_screen_name)
			worksheet.write(iteradorTweets,15,data.in_reply_to_status_id)
			iteradorTweets+=1
				
	workbook.save('tweets-nao-golpe-vem-pra-rua-3.xls')
	time.sleep(5)
	now = datetime.datetime.now()

#This handles Twitter authetification and the connection to Twitter Streaming API