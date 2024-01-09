import "package:flutter/material.dart";

Widget createDialog(BuildContext context, String title, String content,
    Function() onAccept, Function() onCancel, acceptText, cancelText) {
  return AlertDialog(
    title: Text(title),
    content: Text(content),
    actions: [
      TextButton(onPressed: onCancel, child: Text(cancelText)),
      TextButton(onPressed: onAccept, child: Text(acceptText))
    ],
  );
}
