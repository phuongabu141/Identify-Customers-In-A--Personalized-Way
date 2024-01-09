import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:app/views/label/all.dart';

enum LabelNestedRoute {
  view,
  detail,
  tree,
  add;


  String get route {
    return '/$name';
  }

  Widget get widget {
    switch (this) {
      case LabelNestedRoute.view:
        return LabelView();
      case LabelNestedRoute.detail:
        return LabelDetail();
      case LabelNestedRoute.add:
        return LabelAdd();
      case LabelNestedRoute.tree:
        return LabelTree();
    }
  }

  static GetPageRoute getPage(RouteSettings settings) {
    var route = LabelNestedRoute.values
        .firstWhereOrNull((e) => e.route == settings.name);
    return GetPageRoute(page: () => route?.widget ?? Container());
  }
}
