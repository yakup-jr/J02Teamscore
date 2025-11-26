Была проблема с pull requestom, т.к. у ветки main с password-validator не было ничего общего. Поэтому был выполнен merge со следующим флагом:

```console
git merge password-validator --allow-unrelated-histories
```
