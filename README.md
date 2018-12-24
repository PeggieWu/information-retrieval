# 信息检索大作业

## 大作业要求

* 实现对病人病历的检索模型（20分）
* 界面程序（无具体要求，实现基本功能，建议bash纯命令行界面）
* 实验报告（10分）

## 大作业内容

* [病人病历数据库](http://www.trec-cds.org/2017.html) xml格式与txt格式前者是官方给定标准数据集格式，后者是为方便处理。官方文档是两者都可使用的，但是要以xml为准！
* 查询见[topic.xml](./topic.xml)和[extra_topics2017.pdf](./extra_topics2017.pdf) 通常做法是将disease字段作为查询，其他字段作为辅助。
* 提交结果形式：<查询ID> Q0 <> Q0 <文档ID> Q0 <> <文档排序> <文档评分> <系统ID> Q0 <>
* 评价指标——P@10 计算方法 可自己编写，也可以使用trec_eval脚本计算
* 5折交叉验证——3部分训练，1部分验证，1部分测试
* 测试结果取平均

## 完成方法

* 建立[倒排索引](./clinicallevel_cleaned_txt.json)（必做，已从康哲舟出拷贝）
* BM25模型（戚亚涛，已完成）
* 界面（张家瑞，已完成）
* 词干还原（戚亚涛，必做，正在编写）
* 查询扩展（张路，优化，正在编写）
* 寻找医学语料库（张路，已完成）
* 相关反馈，模型训练（戚亚涛，张家瑞，编写）
* 实验报告编写（石瑞聪，卢丽婧）

## 文件说明

* [main.py](./main.py) 主程序文件
* [bm25.py](./bm25.py) BM25模型
* [query.py](./query.py) 查询文件
* [word2vec.py](./word2vec.py) 训练词向量
* [data_helpers.py](./data_helpers.py) 读取数据文件
* [util.py](./util.py) 工具文件
* [test.py](./test.py) 测试编程想法
* [vocab.pkl](./vocab.pkl) 词典文件
* [w2id.pkl](./w2id.pkl) 词与id字典
* [id2w.pkl](./id2w.pkl) id与词字典
