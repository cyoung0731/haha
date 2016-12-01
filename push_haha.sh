#!/bin/bash
########################	
# 文件名：push_haha.sh
# 文件全路径：/home/zcy/dev/git/haha/push_haha.sh
# 作者：zcy
# 创建时间：2016-12-01 16:38:05
########################

echo "----------------------------------------"
echo "上传haha工程开始  `date '+%Y-%m-%d %H:%M:%S'`"
cd `pwd`
git pull
git add --all .
git commit -m "add file `date '+%Y-%m-%d %H:%M:%S'`"
git push
echo "上传haha工程结束 `date '+%Y-%m-%d %H:%M:%S'`"
echo "----------------------------------------"
